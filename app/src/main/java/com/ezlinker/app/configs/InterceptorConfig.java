package com.ezlinker.app.configs;

import com.ezlinker.app.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: ezlinker
 * @description: 拦截器配置
 * @author: wangwenhai
 * @create: 2019-11-07 10:03
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/test/**");
    }

    /**
     * 认证拦截器
     * @return
     */
    @Bean
    public HandlerInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }


}
