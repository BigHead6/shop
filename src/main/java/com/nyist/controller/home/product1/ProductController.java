package com.nyist.controller.home.product1;

import com.github.pagehelper.PageInfo;
import com.nyist.entity.*;
import com.nyist.service.*;
import com.nyist.vo.OrderVo;
import com.nyist.vo.ResultVo;
import com.nyist.vo.shopVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/10 10:06
 * @description：商品
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class ProductController {


    @Autowired
    private ShopcartService shopcartService;
    @Autowired
    private SalesService salesService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SaleItemService saleItemService;
    @Autowired
    private AddressService addressService;

    /**
     * 商品详情介绍
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/introduction")
    public String introduction(HttpServletRequest request, HttpServletResponse response, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.selectById(id);
        model.addAttribute("product", product);
        return "home/introduction";
    }

    @RequestMapping("/pay")
    public String pay(){
        return "home/pay";
    }
    /**
     * 展示商品购买支付页面,并生成订单表
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping("/paylist")
    @ResponseBody
    public ResultVo pay(HttpServletRequest request, HttpServletResponse response, Model model, @RequestBody shopVo[] shopVo  ) {
        //获取前端封装vo对象,根据商品id,查找商品,传单价,总价
        HttpSession session=request.getSession();
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        List<Address> addressList = addressService.selectByCustId(customer.getCustId());
        List<Product> productList = new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        OrderVo orderVo=new OrderVo();
        BigDecimal proTotamt=new BigDecimal(0);
        for (int i=0;i<shopVo.length;i++){
            //单个结算
            if (shopVo[i].getFlag()==1){
                int pid=shopVo[i].getpId();
                Product product = productService.selectById(pid);
                if (product.getSupply()<shopVo[i].getNum()||product.getSupply()==0){
                    return new ResultVo(0,"库存不足",null);
                }
                product.setStorage(shopVo[i].getNum());
                product.setExtend1(shopVo[i].getPrice());
                productList.add(product);
                proTotamt=proTotamt.add(product.getUnitPrice().multiply(new BigDecimal(shopVo[i].getNum())));
            }else {
                //批量结算
                int pid = shopVo[i].getShopId();
                list.add(pid);
                Shopcart shopcart= shopcartService.selectByPrimaryKey(pid);
                Product product = productService.selectById(shopcart.getProdId());
                if (product.getSupply()<shopVo[i].getNum()||product.getSupply()==0){
                    return new ResultVo(0,"库存不足",null);
                }
                product.setStorage(shopVo[i].getNum());
                product.setExtend1((shopVo[i].getPrice()));
                productList.add(product);
                shopVo[i].setpId(shopcart.getProdId());
                proTotamt=proTotamt.add(product.getUnitPrice().multiply(new BigDecimal(shopVo[i].getNum())));
            }

        }
        //生成订单表(发货日期为下单日期的后一天)
        orderVo.setQty(shopVo.length);
        orderVo.setCustId(customer.getCustId());
        orderVo.setTotAmt(proTotamt);
        String orderNo= salesService.insertSelective(orderVo);
        //生成订单从表
        shopVo[0].setOrderNO(orderNo);
        saleItemService.insertSelective(shopVo);
        session.setAttribute("pidList",list);
        session.setAttribute("productList", productList);
        session.setAttribute("addressList", addressList);
        session.setAttribute("total", proTotamt);
        session.setAttribute("orderNo",orderNo);
        return new ResultVo(1,"",null);
    }

    @ResponseBody
    @RequestMapping("/test")
    public PageInfo<Product> test(HttpServletRequest request,
                                  HttpServletResponse response,
                                  HttpSession session,
                                  Product product,
                                  @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        String desc=request.getParameter("name");
        product.setName(desc);
        return productService.pageList(product,currentPage,pageSize);
    }

    @RequestMapping("test1")
    public String index(HttpServletRequest request, HttpServletResponse response,
                        Model model, HttpSession session,
                        String name){

        session.setAttribute("name",name);
        return "home/search";
    }

    @RequestMapping(value = "/zwy")
    public String zyx(HttpServletRequest request,
                      HttpSession session,
                      Model model){
        return "home/search";
    }
}
