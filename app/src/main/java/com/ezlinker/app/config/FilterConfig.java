package com.ezlinker.app.config;

import com.ezlinker.app.filter.LoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @program: ezlinker
 * @description: 过滤器配置
 * @author: wangwenhai
 * @create: 2019-11-11 16:48
 **/
@Configuration

public class FilterConfig {
    @Autowired
    LoggingFilter loggingFilter;

    /**
     * 只在开发阶段保持
     *
     * @return
     */
    @Profile("dev")
    @Bean
    public FilterRegistrationBean loggingFilter() {
        FilterRegistrationBean<LoggingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(loggingFilter);
        registration.addUrlPatterns("/*");
        registration.setName("loggingFilter");
        registration.setOrder(1);
        return registration;
    }

}
