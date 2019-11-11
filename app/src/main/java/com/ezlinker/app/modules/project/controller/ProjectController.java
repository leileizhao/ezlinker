package com.ezlinker.app.modules.project.controller;


import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.project.model.Project;
import com.ezlinker.app.modules.project.service.IProjectService;
import com.ezlinker.common.exchange.QueryCondition;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-06
 */
@RestController
@RequestMapping("/projects")
public class ProjectController extends AbstractXController<Project> {

    @Autowired
    IProjectService iProjectService;

    public ProjectController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    /**
     * 新建项目
     *
     * @param project
     * @return
     */
    @Override
    protected R add(@RequestBody @Valid Project project) {

        return data(project);
    }

    @Override
    protected R delete(@RequestBody Integer[] ids) {
        return null;
    }

    @Override
    protected R update(@RequestBody Project project) {
        return null;
    }

    @Override
    protected R get(@RequestParam Long id) {
        return null;
    }

    @Override
    protected R queryForPage(@RequestBody QueryCondition<Project> queryCondition, int pageNo, int pageSize) {
        return null;
    }


}

