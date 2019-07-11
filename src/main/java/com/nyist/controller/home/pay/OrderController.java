package com.nyist.controller.home.pay;

import com.alipay.api.AlipayApiException;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.nyist.entity.*;
import com.nyist.service.*;
import com.nyist.vo.SaleItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/9 16:34
 * @description：${description}
 * @version: $version$
 */

/**
 * 订单接口
 */
@Controller()
@RequestMapping(value = "${frontPath}")
public class OrderController {
    @Resource
    private PayService payService;
    @Autowired
    private SalesService salesService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ShopcartService shopcartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SaleItemService saleItemService;

    /**
     * 阿里支付
     *
     * @param request
     * @param response
     * @return
     * @throws AlipayApiException
     */
    @GetMapping(value = "alipay")
    @ResponseBody
    public String alipay(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        //获取订单号和地址,生成订单配送表
        String out_trade_no = request.getParameter("orderNo");
        String addressId = request.getParameter("addId");
        //获取订单
        Sales sales = salesService.selectByInvoiceNo(out_trade_no);
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        //生成订单配送表
        if (deliveryService.selectByInvoiceNo(out_trade_no)==null){
            Delivery delivery = new Delivery();
            delivery.setCustId(customer.getCustId());
            delivery.setAddr(addressId);
            delivery.setTotAmt(sales.getTotAmt());
            delivery.setOrderNo(out_trade_no);
            deliveryService.insertSelective(delivery);
        }
        //生成订单名称和订单金额
        AlipayBean alipayBean = new AlipayBean();
        String body = sales.getOrderNo().toString();
        String total_amount = sales.getTotAmt().toString();
        alipayBean.setBody(body);
        alipayBean.setOut_trade_no(out_trade_no);
        alipayBean.setTotal_amount(new StringBuffer().append(total_amount));
        alipayBean.setSubject("shop");
        String result = payService.aliPay(alipayBean);
        return result;
    }

    /**
     * 付款成功
     *
     * @return
     */
    @RequestMapping("/success")
    public String success(HttpServletRequest request, Model model) {
        //付款成功后,更新订单信息,更新购物车信息
        String orderNo = (String) request.getSession().getAttribute("orderNo");
        Sales sales = salesService.selectByInvoiceNo(orderNo);
        salesService.updateByPrimaryKeySelective3(sales,"1");
        //更新购物车信息,显示已付款
        List<Integer> list = (List) request.getSession().getAttribute("pidList");
        for (int i = 0; i < list.size(); i++) {
            shopcartService.updateByShopId(list.get(i));
        }
        //生成配送单
        Delivery delivery = deliveryService.selectByOrderNo(orderNo);
        Address address = addressService.selectByPrimaryKey(Integer.parseInt(delivery.getAddr()));

        //更新商品库存
        List<SaleItemVo> saleItemList= saleItemService.selectProdByInvoiceNo1(orderNo);
        for (int i=0;i<saleItemList.size();i++){
            Product product= productService.selectById(saleItemList.get(i).getProdId());
            product.setSupply(product.getSupply()-saleItemList.get(i).getQty());
            productService.updateByPrimaryKeySelective(product);
        }
        model.addAttribute("total", sales.getTotAmt());
        model.addAttribute("address", address);
        return "home/success";
    }
}
