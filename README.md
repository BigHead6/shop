# shop

#### 介绍
shop1.0

#### 项目介绍
该商城系统采用spring boot2.X，顾客注册采用QQ邮箱发送验证码注册。支付功能为支付宝第三方接口。

#### 环境
1.mysql 5.7
2.maven 3.5.2
3.jdk 1.8
4.IDEA

5.shiro

6.layui+ Amaze ui


#### 使用说明

1. 前台访问路径：127.0.0.1:8765/front/index

   首次登陆需要注册一个用户，然后进行登录

2. 后台访问路径：127.0.0.1:8765/admin/login

   后台账号：admin

   密码：123456

#### 运行时出现的几个问题

**1.邮箱发送验证码失败**

Q:需要将application.yml文件中邮箱配置信息，改为自己信息，同时需要开启自己邮箱的SMTP服务  

![1562854726129](C:\Users\为天下溪\AppData\Local\Temp\1562854726129.png)

password：生成的授权码；

**2.使用支付宝第三方接口**

Q:采用支付宝沙箱环境：账号：sfqydv4102@sandbox.com   支付密码：111111

使用支付宝第三方接口demo：


