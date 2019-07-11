package com.nyist.controller.home.index;

import com.nyist.config.UserToken;
import com.nyist.entity.Customer;
import com.nyist.service.CustomerService;
import com.nyist.vo.ResultVo;
import com.nyist.vo.enums.LoginType;
import com.util.ncryptionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/26 17:11
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class LoginController {
    @Autowired
    private CustomerService customerService;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private static final String USER_LOGIN_TYPE = LoginType.USER.toString();

    /**
     * 登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public ResultVo doLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
        // 创建Subject实例
        Subject currentUser = SecurityUtils.getSubject();
        String error = null;
        // 将用户名及密码封装到UsernamePasswordToken
        UserToken token = new UserToken(username, password,USER_LOGIN_TYPE);
        try {
            currentUser.login(token);
            // 判断当前用户是否登录
            if (currentUser.isAuthenticated() == true) {
                //获取session
                HttpSession session = request.getSession();
                Customer customer= customerService.selectByName(username);
                session.setAttribute("customer",customer);
                return new ResultVo(1,"登陆成功",null);
            }

        } catch (IncorrectCredentialsException e) {
            error = "登录密码错误.";
        } catch (ExcessiveAttemptsException e) {
            error = "登录失败次数过多";
        } catch (LockedAccountException e) {
            error = "帐号已被锁定.";
        } catch (DisabledAccountException e) {
            error = "帐号已被禁用.";
        } catch (ExpiredCredentialsException e) {
            error = "帐号已过期.";
        } catch (UnknownAccountException e) {
            error = "帐号不存在";
        } catch (UnauthorizedException e) {
            error = "您没有得到相应的授权！";
        } catch (Exception e) {
            e.printStackTrace();
        }
       return new ResultVo(0,error,null);
    }

    /**
     * 注册
     * @param request
     * @return
     */
    @RequestMapping("/register1")
    @ResponseBody
    public ResultVo register1(HttpServletRequest request) {
        String checkCode = request.getParameter("checkCode");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String custname=request.getParameter("custname");
        Customer customer=new Customer();
        customer.setUsername(custname);
        customer.setCustCode(password);
        customer.setCustName(username);
        int flag = customerService.registerData(customer);
        if (flag == 1) {
            return new ResultVo(1,"注册成功",null);
        }
        return new ResultVo(2,"该账号已被注册",null);
    }

    /**
     * 登出
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg","安全退出！");
        return "home/login";
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @RequestMapping("/repasswd")
    @ResponseBody
    public ResultVo repasswd(HttpServletRequest request) {
        Customer customer=(Customer)request.getSession().getAttribute("customer");
        String repassword=request.getParameter("repassword");
        String password=request.getParameter("password");
        int flag=ncryptionUtil.comparePassword(password,customer);
        if (flag==1){
            String pw= ncryptionUtil.createPassword(repassword,customer);
            customer.setCustCode(pw);
           if (customerService.updateByPrimaryKeySelective(customer)!=0){
               return new ResultVo(1,"密码修改成功",null);
           }else {
               return new ResultVo(0,"密码修改失败",null);
           }
        }
        return new ResultVo(0,"密码修改失败",null);
    }

}

