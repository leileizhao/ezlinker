package com.ezlinker.app;


import com.ezlinker.app.modules.role.model.Role;
import com.ezlinker.app.modules.role.service.IRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class AppApplicationTests {
    @Resource
    IRoleService roleService;

    @Test
    void addRoles() {
        Role admin = new Role();
        admin.setLabel("系统管理员").setName("ADMIN").setDescription("系统内部管理员").setParent(0L);
        admin.setCreateTime(new Date());

        Role user = new Role();
        user.setLabel("普通用户").setName("USER").setDescription("系统注册用户").setParent(0L);
        user.setCreateTime(new Date());
        roleService.save(admin);
        roleService.save(user);
    }

}
