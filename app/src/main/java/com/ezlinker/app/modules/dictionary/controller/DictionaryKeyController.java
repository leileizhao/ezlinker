package com.ezlinker.app.modules.dictionary.controller;


import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.dictionary.model.DictionaryKey;
import com.ezlinker.app.modules.dictionary.service.IDictionaryKeyService;
import com.ezlinker.app.modules.dictionary.service.IDictionaryValueService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 字典的项 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/dictionaries/key")
public class DictionaryKeyController extends AbstractXController<DictionaryKey> {
    @Autowired
    IDictionaryKeyService iDictionaryKeyService;
    @Autowired
    IDictionaryValueService iDictionaryValueService;

    public DictionaryKeyController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }


    @Override
    protected R add(DictionaryKey dictionaryKey) throws XException {
        boolean ok = iDictionaryKeyService.save(dictionaryKey);
        return ok ? success() : fail();
    }
}

