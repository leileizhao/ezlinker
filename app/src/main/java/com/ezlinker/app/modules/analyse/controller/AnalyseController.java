package com.ezlinker.app.modules.analyse.controller;

import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.product.service.IProductService;
import com.ezlinker.app.modules.project.service.IProjectService;
import com.ezlinker.app.modules.user.service.IUserService;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据统计
 * @author wangwenhai
 */
@RestController
@RequestMapping("/analyse")
public class AnalyseController extends AbstractXController {
    public AnalyseController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Autowired
    IProjectService iProjectService;

    @Autowired
    IProductService iProductService;

    @Autowired
    IUserService iUserService;

    /**
     * 获取一些统计数据
     * TODO: 后期会加入更多统计数据
     *
     * @return
     */
    @GetMapping("/data")
    public R data() {
        Map<String, Object> data = new HashMap<>();
        data.put("projects", iProductService.count());
        data.put("users", iUserService.count());
        data.put("products", iProductService.count());
        return data(data);
    }
}
