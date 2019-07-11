package com.nyist.controller.home.person;

import com.nyist.dao.ReturnMapper;
import com.nyist.entity.*;
import com.nyist.service.ProductService;
import com.nyist.service.ReturnService;
import com.nyist.service.SaleItemService;
import com.nyist.service.SalesService;
import com.nyist.vo.RefundVo;
import com.nyist.vo.ResultVo;
import com.nyist.vo.ReturnsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/16 13:45
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class RefundController {

    @Autowired
    private SalesService salesService;
    @Autowired
    private SaleItemService saleItemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReturnService returnService;
    @Autowired
    private ReturnMapper returnMapper;

    /**
     * 待收货
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/refund")
    public String refund(HttpServletRequest request, Model model) {
        String orderid = request.getParameter("orderid");
        List<SaleItem> saleItemList = saleItemService.selectProdByInvoiceNo(orderid);
        Sales sales = salesService.selectByInvoiceNo(orderid);
        List<RefundVo> refundVos = new ArrayList<>();
        for (int i = 0; i < saleItemList.size(); i++) {
            Product product = productService.selectById(saleItemList.get(i).getProdId());
            RefundVo refundVo = saleItemService.refund(product, saleItemList.get(i));
            refundVos.add(refundVo);
        }
        model.addAttribute("refundVos", refundVos);
        model.addAttribute("sales", sales);
        return "person/refund";
    }


    /**
     * 退货
     *
     * @param request
     * @return
     */
    @RequestMapping("/returnOrder")
    @ResponseBody
    public ResultVo returnOrder(HttpServletRequest request) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String invoiceNo = request.getParameter("invoiceNo");
        String reason = request.getParameter("reason");
        String content = request.getParameter("content");
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        Returns aReturn = new Returns();
        aReturn.setChanNo("56" + invoiceNo);
        aReturn.setChanReason(reason);
        aReturn.setCustId(customer.getCustId());
        aReturn.setContent(content);
        aReturn.setDelivDate(df.format(new Date()));
        aReturn.setOrderNo(invoiceNo);
        Returns returns = returnMapper.selectByOrderNo(invoiceNo);
        if (returns == null) {
            if (returnService.insertSelective(aReturn) != 0) {
                //更新订单表状态->正在退款
                Sales sales=salesService.selectByInvoiceNo(invoiceNo);
                salesService.updateByPrimaryKeySelective3(sales,"2");
                return new ResultVo(1, "退货申请成功", null);
            } else {
                return new ResultVo(0, "退货申请失败", null);
            }
        } else {
            return new ResultVo(0, "退货已申请", null);
        }
    }


    @RequestMapping("/change")
    public String returnList(HttpServletRequest request, Model model) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        //查询退款单
        List<ReturnsVo> returnsList = returnService.selectByCustId(customer.getCustId());
        List<List> lists1 = new ArrayList<>();
        for (int i = 0; i < returnsList.size(); i++) {
            //获取退款单 详细订单
            List<SaleItem> saleItemList1 = saleItemService.findAll(returnsList.get(i).getOrderNo());
            lists1.add(saleItemList1);
        }
        model.addAttribute("salesList1", returnsList);
        model.addAttribute("salesItemList", lists1);

        return "person/change";
    }


}
