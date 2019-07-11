package com.nyist.config;


import com.nyist.shiro.AdminRealm;
import com.nyist.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 为天下溪
 * @Description shiro的配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 常用的过滤器
     * anon：无需认证（登录）可以访问
     * authc：必须认证才可以访问
     * user：如果使用rememberMe的功能可以直接访问
     * perms：该资源必须得到权限资源才可以访问
     * role：该资源必须得到角色权限才可以访问
     */


    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器。匹配原则是最上面的最优先匹配
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接
        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterMap.put("/front/index","anon");
//        filterMap.put("/static/**", "anon");
//        filterMap.put("/static/js/*", "anon");
//        filterMap.put("/static/css*/*", "anon");
//        filterMap.put("/static/images/*", "anon");

        //filterMap.put("/register","anon");
        //filterMap.put("/index","anon");
        //filterMap.put("/getCheckCode","anon");
        //filterMap.put("/register1","anon");
        //filterMap.put("/*","authc");
        // 剩余请求需要身份认证
      //  filterMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
       // shiroFilterFactoryBean.setLoginUrl("/admin/login");
        // 未授权界面;
       // shiroFilterFactoryBean.setUnauthorizedUrl("/404");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "UserRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    @Bean(name = "AdminRealm")
    public AdminRealm adminRealm() {
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return adminRealm;
    }

    /**
     * 安全管理器
     * 配置SecurityManager,
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setAuthenticator(modularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        //设置多Realm
        realms.add(userRealm());
        realms.add(adminRealm());
        securityManager.setRealms(realms);
        //自定义缓存实现,使用redis
        //securityManager.setCacheManager(ehCacheManager());
        //自定义session管理,使用redis
        //securityManager.setRememberMeManager(cookieRememberMeManager());
        //注入记住我管理器;
        //securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 系统自带的Realm管理,主要针对多个realm
     * @return
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }



    /**
     * 密码匹配凭证管理器
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 采用MD5方式加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }
}
