package com.nyist.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 应用启动加载文件
 *
 * @author 为天下溪
 */
@Component
public class PropertiesConfig implements ApplicationListener {
    /**
     * 保存加载配置参数
     */
    private static Map<String, String> aliPropertiesMap = new HashMap<String, String>();

    /**
     * 获取配置参数值
     */
    public static String getKey(String key) {
        return aliPropertiesMap.get(key);
    }

    /**
     * 监听启动完成，执行配置加载到aliPropertiesMap
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            //应用启动加载
            this.init(aliPropertiesMap);
        }
    }

    /**
     * 初始化加载aliPropertiesMap
     */
    public void init(Map<String, String> map) {
        // 获得PathMatchingResourcePatternResolver对象
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //加载resource文件(也可以加载resources)
            Resource resources = resolver.getResource("classpath:config/alipay.properties");
            PropertiesFactoryBean config = new PropertiesFactoryBean();
            config.setLocation(resources);
            config.afterPropertiesSet();
            Properties prop = config.getObject();
            //循环遍历所有得键值对并且存入集合
            for (String key : prop.stringPropertyNames()) {
                map.put(key, (String) prop.get(key));
            }
        } catch (Exception e) {
            new Exception("配置文件加载失败");
        }
    }
}
