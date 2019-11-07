package com.ezlinker.app;

import com.ezlinker.app.modules.component.model.ComponentType;
import com.ezlinker.app.modules.component.service.IComponentTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class AppApplicationTests {

    @Resource
    IComponentTypeService iComponentTypeService;

    @Test
    void contextLoads() {
        ComponentType componentType = new ComponentType();
        componentType.setName("图表").setValue(1).setX(false);
        componentType.setCreateTime(new Date());
        iComponentTypeService.save(componentType);
    }

}
