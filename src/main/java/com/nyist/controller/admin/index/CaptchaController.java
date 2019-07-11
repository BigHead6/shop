package com.nyist.controller.admin.index;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.nyist.config.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping(value = "${adminPath}")
public class CaptchaController {

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     * @param request
     * @param response
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response){

        //设置不缓存图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);

        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(150, 50, 4, 10);

        //将验证码放到HttpSession里面
        request.getSession().setAttribute(Constants.VALIDATE_CODE, captcha);
        // LOGGER.info("本次生成的验证码为[" + captcha.getCode() + "],已存放到HttpSession中");
        //指定生成的响应图片
        response.setContentType("image/jpeg");
        //写给浏览器
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            captcha.write(sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                sos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
