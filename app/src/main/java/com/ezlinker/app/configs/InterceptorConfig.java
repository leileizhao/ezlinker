package com.ezlinker.app.configs;

import com.ezlinker.app.interceptor.PermissionInterceptor;
import com.ezlinker.app.interceptor.RoleInterceptor;
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


    /**
     * "/test"
     * "/login"
     * "/signUp"
     * "/logOut"
     * "/captcha"
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        /**
         * 开发阶段全部放行
         */
        //registry.addInterceptor(roleInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/test/**", "/captcha");
        //registry.addInterceptor(permissionInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/test/**", "/captcha");

    }

    /**
     * 角色拦截器
     *
     * @return
     */
    @Bean
    public HandlerInterceptor roleInterceptor() {
        return new RoleInterceptor();
    }

    /**
     * 权限拦截器
     *
     * @return
     */

    @Bean
    public HandlerInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }
}
