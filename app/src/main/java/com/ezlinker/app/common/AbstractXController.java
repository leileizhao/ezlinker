package com.ezlinker.app.common;

import com.ezlinker.app.utils.UserDetail;
import com.ezlinker.app.utils.UserTokenUtil;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.QueryCondition;
import com.ezlinker.common.exchange.R;
import com.ezlinker.common.exchange.RCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
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
    protected UserDetail getUserDetail() throws XException {
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            return UserTokenUtil.parse(token);
        } else {
            throw new XException(401, "Token has expired,please login again", "Token已经过期,请重新获取");
        }
    }

    /**
     * 添加一个T
     *
     * @param t
     * @return
     */
    @PostMapping("/add")
    protected abstract R add(T t);

    /**
     * 根据条件删除T
     *
     * @param queryCondition
     * @return
     */
    @DeleteMapping("/delete")
    protected abstract R delete(QueryCondition<T> queryCondition);

    /**
     * 批量删除T
     *
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    protected abstract R batchDelete(Integer[] ids);

    /**
     * 更新T
     *
     * @param t
     * @return
     */
    @RequestMapping("/update")
    protected abstract R update(T t);

    /**
     * 查询单个T
     *
     * @param queryCondition
     * @return
     */
    @RequestMapping("/get")
    protected abstract R get(QueryCondition<T> queryCondition);

    /**
     * 条件查询T列表
     *
     * @param queryCondition
     * @return
     */
    @RequestMapping("/list")
    protected abstract R list(QueryCondition<T> queryCondition);

    /**
     * 附带检索条件的分页查询
     *
     * @param queryCondition
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("page")
    protected abstract Object page(QueryCondition<T> queryCondition, int pageNo, int pageSize);

    /**
     * 失败返回
     *
     * @return
     */
    protected R fail() {
        Integer code = RCode.FAIL.getCode();
        String message = RCode.FAIL.getMessage();
        String i8nMessage = RCode.FAIL.getI8nMessage();
        return new R(code, message, i8nMessage, null);
    }

    /**
     * 成功返回
     *
     * @return
     */
    protected R success() {
        Integer code = RCode.SUCCESS.getCode();
        String message = RCode.SUCCESS.getMessage();
        String i8nMessage = RCode.SUCCESS.getI8nMessage();
        return new R(code, message, i8nMessage, null);
    }

    /**
     * 自定义成功返回状态码
     *
     * @param data
     * @return
     */
    protected R data(Object data) {
        Integer code = RCode.SUCCESS.getCode();
        String message = RCode.SUCCESS.getMessage();
        String i8nMessage = RCode.SUCCESS.getI8nMessage();
        return new R(code, message, i8nMessage, data);
    }


}
