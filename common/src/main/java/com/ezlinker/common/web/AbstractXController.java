package com.ezlinker.common.web;

import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.QueryCondition;
import com.ezlinker.common.exchange.R;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
     * @param t
     * @return
     */
    @PostMapping("/add")
    public abstract R add(@RequestBody T t);

    /**
     * @param queryCondition
     * @return
     */
    @DeleteMapping("/delete")
    public abstract R delete(@RequestBody QueryCondition<T> queryCondition);

    /**
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    public abstract R batchDelete(@RequestParam("ids[]") Integer[] ids);

    /**
     * @param t
     * @return
     */
    @RequestMapping("/update")
    public abstract R update(@RequestBody T t);

    /**
     * @param queryCondition
     * @return
     */
    @RequestMapping("/get")
    public abstract R get(@RequestBody QueryCondition<T> queryCondition);

    /**
     * @param queryCondition
     * @return
     */
    @RequestMapping("/list")
    public abstract R list(@RequestBody QueryCondition<T> queryCondition);

    /**
     * 附带检索条件的分页查询
     *
     * @param queryCondition
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("page")
    public abstract Object page(@RequestBody QueryCondition<T> queryCondition, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize);

    /**
     * @param msg
     * @return
     */
    public R fail(String msg) {

        return new R();
    }

    /**
     * @return
     */
    public R success() {

        return new R();
    }

    /**
     * @param msg
     * @return
     */
    public R success(String msg) {

        return new R();
    }

    /**
     * @param data
     * @param msg
     * @return
     */
    public R success(Object data, String msg) {

        return new R();
    }

}
