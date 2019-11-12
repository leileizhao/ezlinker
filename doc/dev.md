# 开发约束和规范
## 1. 框架
框架部分无需开发，一般为公共部分。
## 2. 业务
### 1. CURD代码模板
1. 运行`com.ezlinker.app.utils`下面的`CodeGenerator`
2. Controller序号实现4个基本方法，一般为公共的部分，自定义接口严格遵守RestFul风格，下面是一个模板：
```java

package com.ezlinker.app.modules.project.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.project.model.Project;
import com.ezlinker.app.modules.project.service.IProjectService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>
 * 项目 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
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
     * 创建项目
     *
     * @param project 项目:必传
     * @return
     */
    @Override
    protected R add(@RequestBody Project project) throws XException {
        project.setUserId(getUserDetail().getId());
        iProjectService.save(project);
        return success();
    }

    /**
     * 删除项目
     *
     * @param ids 批量删除的ID数组:必传
     * @return
     */

    @Override
    protected R delete(@RequestBody Integer[] ids) {
        boolean ok = iProjectService.removeByIds(Arrays.asList(ids));
        return ok ? success() : fail();
    }

    /**
     * 更新项目信息
     *
     * @param project 项目:必传
     * @return
     * @throws XException
     */
    @Override
    protected R update(@RequestBody Project project) throws XException {
        if (project.getId() == null) {
            throw new XException("Invalid param!", "参数缺失");
        }
        if (iProjectService.getById(project.getId()) == null) {
            throw new XException("Project not exists!", "项目不存在");
        }
        boolean ok = iProjectService.updateById(project);
        return ok ? success() : fail();
    }


    /**
     * 查看项目详情
     *
     * @param id 项目ID:必传
     * @return
     */

    @Override
    protected R get(@PathVariable Long id) {

        return data(iProjectService.getById(id));
    }

    /**
     * 条件检索
     *
     * @param self     如果传self，则查询和自己有关的，不传则查询所有：选传
     * @param name     项目名称：选传
     * @param location 项目位置：选传
     * @param pageNo   页码：必传
     * @param pageSize 页长：必传
     * @return
     * @throws XException
     */
    @GetMapping
    public R queryForPage(
            @RequestParam(required = false) String self,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam int pageNo,
            @RequestParam int pageSize) throws XException {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        if (self != null) {
            queryWrapper.eq("user_id", getUserDetail().getId());

        }
        queryWrapper.like(name != null, "name", location);
        queryWrapper.like(location != null, "location", location);
        IPage<Project> projectPage = iProjectService.page(new Page<>(pageNo, pageSize), queryWrapper);

        return data(projectPage);
    }
}


```
### 3. 返回状态码
1. 通用状态码

| 状态码 | 含义                                                  |
| ------ | -----------------------------------------------------|
| 200    | HTTP请求执行成功后返回                                |
| 202    | 异步任务创建成功                                      |
| 400    | HTTP请求执行失败后返回                                |
| 401    | 表示用户没有权限（令牌、用户名、密码错误）            |
| 402    | Token异常            |      |
| 403    | 表示用户得到授权（与401错误相对），但是访问是被禁止的 |
| 404    | 资源不存在                                            |
| 500    | 系统内部错误                                            |
| 501    | 普通业务执行失败                                            |

2. 具体业务状态码，自己添加
> 一般为特有的业务状态码，其他的全部用公共部分即可

| 状态码 | 含义                                                  |
| ------ | ----------------------------------------------------- |
| XXX    | 短信验证码发送失败(阿里云欠费)                                            |
