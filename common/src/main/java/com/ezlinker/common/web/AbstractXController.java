package com.ezlinker.common.web;

import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.QueryCondition;
import com.ezlinker.common.exchange.R;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: ezlinker
 * @description: 基础Controller
 * @author: wangwenhai
 * @create: 2019-11-04 17:10
 **/
public abstract class AbstractXController<T> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * httpServletRequest
     */
    private HttpServletRequest httpServletRequest;

    public AbstractXController(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * 获取当前用户的信息
     *
     * @return
     */
    public User getCurrentUserInfo() throws XException {
        String token = httpServletRequest.getHeader("token");
        return null;
    }

    /**
     * 添加一个T
     * @param t
     * @return
     */
    @PostMapping("/add")
    public abstract R add(T t);

    /**
     * 根据条件删除T
     * @param queryCondition
     * @return
     */
    @DeleteMapping("/delete")
    public abstract R delete(QueryCondition<T> queryCondition);

    /**
     * 批量删除T
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    public abstract R batchDelete(Integer[] ids);

    /**
     * 更新T
     * @param t
     * @return
     */
    @RequestMapping("/update")
    public abstract R update(T t);

    /**
     * 查询单个T
     * @param queryCondition
     * @return
     */
    @RequestMapping("/get")
    public abstract R get(QueryCondition<T> queryCondition);

    /**
     * 条件查询T列表
     * @param queryCondition
     * @return
     */
    @RequestMapping("/list")
    public abstract R list(QueryCondition<T> queryCondition);

    /**
     * 附带检索条件的分页查询
     *
     * @param queryCondition
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("page")
    public abstract Object page(QueryCondition<T> queryCondition, int pageNo, int pageSize);

    /**
     * 失败返回
     * @param msg
     * @return
     */
    public R fail(String msg) {

        return new R();
    }

    /**
     * 成功返回
     * @return
     */
    public R success() {

        return new R();
    }

    /**
     * 自定义成功返回消息
     * @param msg
     * @return
     */
    public R success(String msg) {

        return new R();
    }

    /**
     * 自定义成功返回状态码
     * @param data
     * @param msg
     * @return
     */
    public R success(Object data, String msg) {

        return new R();
    }

}
