package com.nyist;


import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;


/**
 * 后台密码修改
 */
public class zwytest {


    public static void main(String[] args){  //盐加密
        String password = "123";
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 1024;
        String algorithmName = "md5";

        String password1 = new SimpleHash(algorithmName, password, "admin", times).toString();
        System.out.printf("原始密码是 %s , 盐是： %s, 运算次数是： %d, 运算出来的密文是：%s ",password,salt,times,password1);
    }
}

