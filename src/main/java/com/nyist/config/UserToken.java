package com.nyist.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/28 17:06
 * @description：${description}
 * @version: $version$
 */
public class UserToken extends UsernamePasswordToken {
    /**
     * 登录类型，判断是客户登录，还是管理员登录
     * */
    private String loginType;

    public UserToken(final String username, final String password,String loginType) {
        super(username,password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}
