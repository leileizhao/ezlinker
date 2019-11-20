package com.ezlinker.app.modules.internalmessage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.internalmessage.model.InternalMessage;
import com.ezlinker.app.modules.internalmessage.service.InternalMessageService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>
 * 站内信 前段控制器
 * </p>
 *
 * @author lc5900
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/internalMessages")
public class InternalMessageController extends AbstractXController<InternalMessage> {

    private InternalMessageService internalMessageService;

    public InternalMessageController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Autowired
    public void setInternalMessageService(InternalMessageService internalMessageService) {
        this.internalMessageService = internalMessageService;
    }

    /**
     * 删除站内信
     *
     * @param ids 站内信id
     * @return
     * @throws XException
     */
    @Override
    protected R delete(@PathVariable Integer[] ids) throws XException {
        boolean ok = internalMessageService.removeByIds(Arrays.asList(ids));
        return ok ? success() : fail();
    }


    /**
     * 分页检索
     *
     * @param current   页码：必传
     * @param size 页长：必传
     * @return
     * @throws XException
     */
    @GetMapping
    public R queryForPage(
            @RequestParam Integer current,
            @RequestParam Integer size) throws XException {
        QueryWrapper<InternalMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", getUserDetail().getId()).eq("marked", 0);
        queryWrapper.orderByDesc("create_time");
        IPage<InternalMessage> internalMessagePage = internalMessageService.page(new Page<>(current, size), queryWrapper);

        return data(internalMessagePage);
    }

    /**
     * 标记站内信
     *
     * @param id
     * @return
     * @throws XException
     */
    @PutMapping("/mark/{id}")
    public R mark(@PathVariable Long id) throws XException {
        QueryWrapper<InternalMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        InternalMessage internalMessage = internalMessageService.getById(id);
        if (internalMessage == null) {
            throw new XException("InternalMessage not exists!", "站内信不存在");

        }
        internalMessage.setMarked(1);
        boolean ok = internalMessageService.updateById(internalMessage);
        return ok ? success() : fail();
    }
}
