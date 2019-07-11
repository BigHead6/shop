package com.nyist.controller.home.user;

import com.nyist.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/25 22:13
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class MailController {
    @Autowired
    private MailService mailService;

    /**
     * 邮箱注册
     * @param email
     * @return
     */
    @RequestMapping("getCheckCode")
    @ResponseBody
    public String getCheckCode(String email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(email, "注册验证码", message);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return checkCode;
    }
}
