package com.ezlinker.app.common;

import com.ezlinker.app.utils.UserDetail;
import com.ezlinker.app.utils.UserTokenUtil;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.QueryCondition;
import com.ezlinker.common.exchange.R;
import com.ezlinker.common.exchange.RCode;
import com.ezlinker.common.model.XFrom;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: ezlinker
 * @description: 基础Controller
 * @author: wangwenhai
 * @create: 2019-11-04 17:10
 **/
public abstract class AbstractXController<T> {


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
     * @param form
     * @return
     */
    @PostMapping
    protected abstract R add(@RequestBody T t);

    /**
     * 批量删除T
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    protected abstract R delete(@RequestBody Integer[] ids);

    /**
     * 更新T
     *
     * @param t
     * @return
     */
    @PutMapping
    protected abstract R update(@RequestBody T t);

    /**
     * 查询单个T
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    protected abstract R get(@RequestParam("id") Long id);

    /**
     * 附带检索条件的分页查询
     *
     * @param queryCondition
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping
    protected abstract R queryForPage(@RequestBody QueryCondition<T> queryCondition, int pageNo, int pageSize);

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
