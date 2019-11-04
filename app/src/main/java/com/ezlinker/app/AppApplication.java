package com.ezlinker.app;

import com.ezlinker.common.exchange.R;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwenhai
 */
@MapperScan(basePackages = " com.ezlinker.app.modules.*.mapper")
@ComponentScan(basePackages = {"com.ezlinker.*"})
@SpringBootApplication
@RestController
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @GetMapping("/")
    public R i(){
        return new R(200,"启动成功");
    }
}
