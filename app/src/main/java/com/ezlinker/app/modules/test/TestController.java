package com.ezlinker.app.modules.test;

import com.ezlinker.app.modules.role.service.IRoleService;
import com.ezlinker.common.exchange.QueryCondition;
import com.ezlinker.common.exchange.R;
import com.ezlinker.app.common.AbstractXController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: ezlinker
 * @description: 测试
 * @author: wangwenhai
 * @create: 2019-11-06 15:18
 **/
@RestController("/test")
public class TestController extends AbstractXController<R> {
    public TestController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    public R add(R r) {
        return null;
    }

    @Override
    public R delete(QueryCondition<R> queryCondition) {
        return null;
    }

    @Override
    public R batchDelete(Integer[] ids) {
        return null;
    }

    @Override
    public R update(R r) {
        return null;
    }

    @Override
    public R get(QueryCondition<R> queryCondition) {
        return null;
    }

    @Override
    public R list(QueryCondition<R> queryCondition) {
        return null;
    }

    @Override
    public Object page(QueryCondition<R> queryCondition, int pageNo, int pageSize) {
        return null;
    }

    @Autowired
    IRoleService roleService;

    @GetMapping("/")
    public R i() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("姓名", "功夫熊猫");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "kungfu panda");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("팬더", "시간 팬더");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        return new R(0, "Startup success!", "启动成功;성공을 시작하다;起動成功;Avvio successo", list);
    }

    @GetMapping("/s")
    public R s() {
        return success();
    }

    @GetMapping("/f")
    public R f() {
        return fail();
    }

    @GetMapping("/api")
    public R api() {
        return success();
    }

}
