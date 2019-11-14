package com.ezlinker.app.modules.dictionary.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ezlinker.app.common.AbstractXController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 字典的值 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/dictionaries/value")
public class DictionaryValueController extends AbstractXController {

    public DictionaryValueController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }
}

