package com.nyist.controller.admin.index;

import cn.hutool.captcha.CircleCaptcha;
import com.nyist.config.Constants;
import com.nyist.config.UserToken;
import com.nyist.entity.Customer;
import com.nyist.entity.SysAuthority;
import com.nyist.service.CustomerService;
import com.nyist.service.SysAuthorityService;
import com.nyist.vo.ResultVo;
import com.nyist.vo.enums.LoginType;
import org.apache.commons.lang.StringUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/26 17:11
 * @description：后台登录,登出
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class AdminLoginController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SysAuthorityService sysAuthorityService;
    private static final String ADMIN_LOGIN_TYPE = LoginType.ADMIN.toString();

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/doLogin1")
    @ResponseBody
    public ResultVo doLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("vercode") String vercode,
                            HttpServletRequest request, HttpServletResponse response) {
        String error = null;
        HttpSession session = request.getSession();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return null;
        }
        if (StringUtils.isBlank(vercode)) {
            return null;
        }
        if (session == null) {
            return null;
        }
        // 从Session中获取验证码 判断验证码是否已过期（验证码是放在session中，过期后页面会报500）
        if ((CircleCaptcha) session.getAttribute(Constants.VALIDATE_CODE) == null) {
            return null;
        }
        CircleCaptcha captcha = (CircleCaptcha) session.getAttribute(Constants.VALIDATE_CODE);
        if (StringUtils.isBlank(captcha.getCode())) {
            return null;
        }
        else {
            // 创建Subject实例
            Subject currentUser = SecurityUtils.getSubject();
            // 将用户名及密码封装到UsernamePasswordToken
            UserToken token = new UserToken(username, password,ADMIN_LOGIN_TYPE);
            try {
                currentUser.login(token);
                // 判断当前用户是否登录
                if (currentUser.isAuthenticated() == true) {
                    //获取session
                    SysAuthority sysAuthority = sysAuthorityService.selectByName(username);
                    session.setAttribute("sysAuthority", sysAuthority);
                    return new ResultVo(1, "登陆成功", null);
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
        }
        return new ResultVo(0, error, null);
    }


    /**
     * 登出
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/logout1")
    public String logout(HttpSession session, Model model) {
        session.removeAttribute("sysAuthority");
        return "admin/login";
    }

    /**
     * 重置密码
     *
     * @param request
     * @return
     */
    @RequestMapping("/repassword1")
    public String repassword1(HttpServletRequest request) {
        String repassword = request.getParameter("repassword");
        return "admin/login";
    }

}

