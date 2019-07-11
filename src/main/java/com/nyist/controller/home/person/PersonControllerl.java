package com.nyist.controller.home.person;

import com.nyist.dao.ProductMapper;
import com.nyist.entity.*;
import com.nyist.service.*;
import com.nyist.vo.RefundVo;
import com.nyist.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/10 10:17
 * @description：个人中心
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class PersonControllerl {

    @Autowired
    private SalesService salesService;

    @Autowired
    private SaleItemService saleItemService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private ProductService productService;


    /**
     * 个人中心首页
     *
     * @return
     */
    @RequestMapping("/person")
    public String index2(HttpServletRequest request, Model model) {
        //待付款
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        List<Sales> salesList = salesService.selectByCustId(customer.getCustId());
        model.addAttribute("nopay", salesList.size());
        //待发货
        List<Sales> salesList1 = salesService.selectByCustId1(customer.getCustId());
        model.addAttribute("waitnum", salesList1.size());

        //待收货
        List<Sales> salesList2 = salesService.selectByCustId2(customer.getCustId());
        model.addAttribute("waitsend", salesList2.size());

        //收藏夹
        List<Product> productList = collectService.findAll(customer.getCustId());
        model.addAttribute("productList", productList);

        return "person/index";
    }

    /**
     * 收货地址
     *
     * @return
     */
    @RequestMapping("/address")
    public String address(HttpServletRequest request, Model model) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        List<Address> addressList = addressService.selectByCustId(customer.getCustId());
        model.addAttribute("addressList", addressList);
        return "person/address";
    }

    /**
     * 个人信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/information")
    public String information(HttpServletRequest request, Model model) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        model.addAttribute("customer", customer);
        return "person/information";
    }


    /**
     * 订单详情
     *
     * @return
     */
    @RequestMapping("/order")
    public String order(HttpServletRequest request, Model model) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        //获取所有订单
        List<Sales> salesList = salesService.findAll(customer.getCustId());
        List<List> lists = new ArrayList<>();
        for (int i = 0; i < salesList.size(); i++) {
            //获取详细订单
            List<SaleItem> saleItemList = saleItemService.findAll(salesList.get(i).getInvoiceNo());
            lists.add(saleItemList);
        }
        model.addAttribute("salesList", salesList);
        model.addAttribute("salesItemList", lists);


        //查询待付款订单
        List<Sales> salesList1 = salesService.selectByCustId(customer.getCustId());
        List<List> lists1 = new ArrayList<>();
        for (int i = 0; i < salesList1.size(); i++) {
            //获取未付款详细订单
            List<SaleItem> saleItemList1 = saleItemService.findAll(salesList1.get(i).getInvoiceNo());
            lists1.add(saleItemList1);
        }
        model.addAttribute("salesList1", salesList1);
        model.addAttribute("salesItemList1", lists1);


        //查询待发货订单(发货日期为下单日期的后一天)
        //待发货
        List<Sales> salesList2 = salesService.selectByCustId1(customer.getCustId());
        List<List> lists2 = new ArrayList<>();
        for (int i = 0; i < salesList2.size(); i++) {
            //获取未付款详细订单
            List<SaleItem> saleItemList2 = saleItemService.findAll(salesList2.get(i).getInvoiceNo());
            lists2.add(saleItemList2);
        }
        model.addAttribute("salesList2", salesList2);
        model.addAttribute("salesItemList2", lists2);


        //查询待收货订单(当前时间超过发货日期)
        List<Sales> salesList3 = salesService.selectByCustId2(customer.getCustId());
        List<List> lists3 = new ArrayList<>();
        for (int i = 0; i < salesList3.size(); i++) {
            //获取未付款详细订单
            List<SaleItem> saleItemList3 = saleItemService.findAll(salesList3.get(i).getInvoiceNo());
            lists3.add(saleItemList3);
        }
        model.addAttribute("salesList3", salesList3);
        model.addAttribute("salesItemList3", lists3);
        return "person/order";
    }

    /**
     * 个人中心订单支付
     *
     * @param request
     * @return
     */
    @RequestMapping("/paylist1")
    public String pay1(HttpServletRequest request) {
        //根据订单号查找所属商品,获取地址
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        HttpSession session = request.getSession();
        String invoiceNo = request.getParameter("id");
        List<Address> addressList = addressService.selectByCustId(customer.getCustId());
        List<SaleItem> list = saleItemService.selectProdByInvoiceNo(invoiceNo);
        Sales sales = salesService.selectByInvoiceNo(invoiceNo);
        List<Product> productList = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Product product = productService.selectById(list.get(i).getProdId());
            productList.add(product);
            list2.add(list.get(i).getProdId());
        }
        session.setAttribute("pidList", list2);
        session.setAttribute("productList", productList);
        session.setAttribute("addressList", addressList);
        session.setAttribute("total", sales.getTotAmt());
        session.setAttribute("orderNo", sales.getInvoiceNo());
        return "home/pay";
    }

    /**
     * 删除订单,
     *
     * @param request
     * @return
     */
    @PostMapping("/delOrder")
    @ResponseBody
    public ResultVo delOrder(HttpServletRequest request) {
        String invoiceNo = request.getParameter("invoiceNo");
        int flag = salesService.updateByInvoiceNo(invoiceNo);
        if (flag != 0) {
            return new ResultVo(1, "删除成功", null);
        } else {
            return new ResultVo(0, "删除失败", null);
        }
    }

    /**
     * 添加收藏夹
     *
     * @param request
     * @return
     */
    @RequestMapping("/addCollect")
    @ResponseBody
    public ResultVo addCollect(HttpServletRequest request) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int pid = Integer.parseInt(request.getParameter("pid"));
        Collect collect = new Collect();
        collect.setCustId(customer.getCustId());
        collect.setProdId(pid);
        Collect collect1 = collectService.selectByCollect(collect);
        ResultVo resultVo = new ResultVo();
        if (collect1 != null) {
            resultVo.setCode(0);
            resultVo.setMsg("该商品已收藏");
        } else {
            int flag = collectService.insertSelective(collect);
            if (flag == 1) {
                resultVo.setCode(1);
                resultVo.setMsg("收藏成功");
            } else {
                resultVo.setCode(2);
                resultVo.setMsg("收藏失败");
            }
        }
        return resultVo;
    }

    /**
     * 移除收藏夹
     *
     * @param request
     * @return
     */
    @RequestMapping("/delCollect")
    @ResponseBody
    public ResultVo delCollect(HttpServletRequest request) {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int pid = Integer.parseInt(request.getParameter("id"));
        //根据商品id和顾客id更新收藏表
        Collect collect = new Collect();
        collect.setCustId(customer.getCustId());
        collect.setProdId(pid);
        collectService.updateByPrimaryKeySelective(collect);
        return new ResultVo(1, "移除成功", null);
    }

    /**
     * 删除地址接口
     * @param request
     * @return
     */
    @RequestMapping("/delAddress")
    @ResponseBody
    public ResultVo delAddress(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Customer customer=(Customer) request.getSession().getAttribute("customer");
        int id = Integer.parseInt(request.getParameter("id"));
        //根据地址id更新地址表
        addressService.deleteByPrimaryKey(id);
        List<Address> addressList = addressService.selectByCustId(customer.getCustId());
        session.setAttribute("addressList", addressList);
        return new ResultVo(1, "删除成功", null);
    }



    @RequestMapping("/bill")
    public String bill() {
        return "person/bill";
    }

    @RequestMapping("/billlist")
    public String billlist() {
        return "person/billlist";
    }

    @RequestMapping("/bindphone")
    public String bindphone() {
        return "person/bindphone";
    }

    @RequestMapping("/blog")
    public String blog() {
        return "person/blog";
    }


    @RequestMapping("/email")
    public String email() {
        return "person/email";
    }

    @RequestMapping("/idcard")
    public String idcard() {
        return "person/idcard";
    }

    @RequestMapping("/orderinfo")
    public String orderinfo() {
        return "person/orderinfo";
    }

    @RequestMapping("/password")
    public String password() {
        return "person/password";
    }

    @RequestMapping("/question")
    public String question() {
        return "person/question";
    }

    @RequestMapping("/record")
    public String record() {
        return "person/record";
    }



    @RequestMapping("/safety")
    public String safety() {
        return "person/safety";
    }


    @RequestMapping("/setpay")
    public String setpay() {
        return "person/setpay";
    }
}
