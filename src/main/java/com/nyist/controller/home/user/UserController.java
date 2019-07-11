package com.nyist.controller.home.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nyist.entity.*;
import com.nyist.service.*;
import com.nyist.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date ：Created in 2019/2/22 10:25
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class UserController {

    @Autowired
    private AddressService addressService;
    @Autowired
    private CustomerService customerService;


    /**
     * 添加地址
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("address1")
    @ResponseBody
    public ResultVo address(HttpServletRequest request, HttpServletResponse response, @RequestBody addressVo addressVo) {

        Customer customer = (Customer) request.getSession().getAttribute("customer");

        Address address = new Address();
        address.setAddress(addressVo.getAddress1());
        address.setCity(addressVo.getCity());
        address.setConsignee(addressVo.getShouhuoer());
        address.setCustId(customer.getCustId());
        address.setDistrict(addressVo.getQu());
        address.setProvince(addressVo.getShengfen());
        address.setPhone(addressVo.getPhone());
        int flag = addressService.insertSelective(address);
        if (flag != 0) {
            return new ResultVo(1, "地址添加成功", null);
        } else {
            return new ResultVo(0, "地址添加失败", null);
        }
    }

    /**
     * 保存个人资料
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("updateinfo")
    @ResponseBody
    public ResultVo updateinfo(HttpServletRequest request, HttpServletResponse response, @RequestBody Customer customer1) {

        Customer customer = (Customer) request.getSession().getAttribute("customer");
        customer.setCustName(customer1.getCustName());
        customer.setSex(customer1.getSex());
        customer.setZip(customer1.getZip());
        customer.setTelNo(customer1.getTelNo());
        customer.setEmail(customer1.getEmail());

        int flag = customerService.updateByPrimaryKeySelective(customer);
        if (flag != 0) {
            return new ResultVo(1, "信息保存成功", null);
        }
        return new ResultVo(0, "信息保存失败", null);

    }


}
