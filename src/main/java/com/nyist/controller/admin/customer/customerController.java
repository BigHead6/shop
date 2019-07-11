package com.nyist.controller.admin.customer;

import com.github.pagehelper.PageInfo;
import com.nyist.entity.Customer;
import com.nyist.entity.SysAuthority;
import com.nyist.service.CustomerService;
import com.nyist.service.SysAuthorityService;
import com.nyist.vo.LayuiPageVo;
import com.nyist.vo.ResultVo;
import com.util.ncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/9 10:26
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class customerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private SysAuthorityService sysAuthorityService;

    /**
     * 获取顾客信息
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getCustAll")
    @ResponseBody
    public LayuiPageVo getCustAll(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        LayuiPageVo lpv = new LayuiPageVo();
        PageInfo<Customer> pageInfo = null;
        pageInfo = customerService.pageList(currentPage, pageSize);
        List<Customer> productList = pageInfo.getList();
        lpv.setCode(1);
        lpv.setMsg("获取数据成功");
        lpv.setTotal(pageInfo.getTotal());
        lpv.setData(productList);
        return lpv;
    }

    /**
     * 重置顾客密码
     * @return
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    public ResultVo resetPassword(HttpServletRequest request) {
        int custId=Integer.parseInt(request.getParameter("custId"));
        Customer customer =customerService.selectByPrimaryKey(custId);
        String ps=ncryptionUtil.createPassword("123456",customer);
        customer.setCustCode(ps);
        if (customerService.updateByPrimaryKeySelective(customer)!=0){
            return  new ResultVo(1,"密码重置成功",null);
        }
        return  new ResultVo(0,"密码重置失败",null);
    }
}
