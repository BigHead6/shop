package com.nyist.controller.admin.order;

import com.github.pagehelper.PageInfo;
import com.nyist.dao.CustomerMapper;
import com.nyist.entity.*;
import com.nyist.service.*;
import com.nyist.vo.LayuiPageVo;
import com.nyist.vo.ResultVo;
import com.nyist.vo.SaleItemVo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/17 9:13
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class ReturnController {
    @Autowired
    private SalesService salesService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private SaleItemService saleItemService;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ReturnService returnService;

    /**
     * 获取退货单信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getReturnAll")
    @ResponseBody
    public LayuiPageVo getReturnAll(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        LayuiPageVo lpv = new LayuiPageVo();
        PageInfo<Returns> pageInfo = null;
        pageInfo = returnService.pageList(currentPage, pageSize);
        List<Returns> returnsList = pageInfo.getList();
        lpv.setCode(1);
        lpv.setMsg("获取数据成功");
        lpv.setTotal(pageInfo.getTotal());
        lpv.setData(returnsList);
        return lpv;
    }

    /**
     * 订单详情表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/returnDetails")
    public String returnDetails(HttpServletRequest request, Model model) {
        String chanNo=request.getParameter("chanNo");
        Returns returns=returnService.selectByPrimaryKey(chanNo);
        Address address=new Address();

        Sales sales=salesService.selectByInvoiceNo(returns.getOrderNo());
        Delivery delivery=deliveryService.selectByInvoiceNo(returns.getOrderNo());
        if (delivery==null){
            address=null;
        }else {
            address=addressService.selectByPrimaryKey(Integer.parseInt(delivery.getAddr()));
        }

        List<SaleItemVo> saleItemVos=saleItemService.selectProdByInvoiceNo1(returns.getOrderNo());
        Customer customer=customerMapper.selectByPrimaryKey(sales.getCustId());
        model.addAttribute("customer",customer);
        model.addAttribute("sales",sales);
        model.addAttribute("address",address);
        model.addAttribute("saleItemVos",saleItemVos);
        model.addAttribute("returns",returns);

        return "admin/returnDetails";
    }


    /**
     * 退款操作(1:确定退款,0:拒绝退款)
     * @param request
     * @return
     */
    @RequestMapping("/operation")
    @ResponseBody
    public ResultVo operation(HttpServletRequest request) {
        //获取退款编号,更新退货表和订单表
        String chanNo=request.getParameter("chanNo");
        int flag=Integer.parseInt(request.getParameter("flag"));
        String money=request.getParameter("money");
        Returns returns= returnService.selectByPrimaryKey(chanNo);
        Sales sales=salesService.selectByInvoiceNo(returns.getOrderNo());
        if (flag==1){
            if (!returns.getStatus().equals("0")){
                return new ResultVo(2,"该退款状态无法退款",null);
            }
            returns.setStatus("1");
            returnService.updateByPrimaryKeySelective(returns);
            salesService.updateByPrimaryKeySelective3(sales,"3");
            return new ResultVo(1,"退款成功",null);
        }else {
            if (!returns.getStatus().equals("0")){
                return new ResultVo(2,"该退款状态无法退款",null);
            }
            returns.setStatus("2");
            salesService.updateByPrimaryKeySelective3(sales,"2");
            returnService.updateByPrimaryKeySelective(returns);
            return new ResultVo(1,"拒绝退款",null);
        }
    }


    @RequestMapping("/delReturns")
    @ResponseBody
    public ResultVo delReturns(HttpServletRequest request) {
        String chanNo=request.getParameter("chanNo");
        Returns returns=returnService.selectByPrimaryKey(chanNo);
        returns.setFlag("0");
        returnService.updateByPrimaryKeySelective(returns);
        return new ResultVo(1,"删除成功",null);
    }




}
