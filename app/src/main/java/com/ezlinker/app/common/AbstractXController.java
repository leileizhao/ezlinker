package com.ezlinker.app.common;

import com.ezlinker.app.modules.user.model.UserDetail;
import com.ezlinker.app.utils.UserTokenUtil;
import com.ezlinker.common.exception.TokenException;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import com.ezlinker.common.exchange.RCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: ezlinker
 * @description: 基础Controller
 * @author: wangwenhai
 * @create: 2019-11-04 17:10
 **/
public abstract class AbstractXController<T> extends SimpleXController {


    public AbstractXController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    /**
     * 添加一个T
     *
     * @param t
     * @return
     */
    @PostMapping
    protected R add(T t) throws XException {
        return success();
    }


    /**
     * 批量删除T
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    protected R delete(Integer[] ids) throws XException {
        return success();
    }


    /**
     * 更新T
     *
     * @param t
     * @return
     */
    @PutMapping(value = "/{id}")
    protected R update(Long id, T t) throws XException {
        return success();
    }

    /**
     * 查询单个T
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    protected R get(Long id) throws XException {
        return success();
    }

}
