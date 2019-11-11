package com.ezlinker.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangwenhai
 */
@MapperScan(basePackages = "com.ezlinker.app.modules.*.mapper")
@ComponentScan(basePackages = {"com.ezlinker.*"})
@SpringBootApplication
@EnableConfigurationProperties
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }


}
