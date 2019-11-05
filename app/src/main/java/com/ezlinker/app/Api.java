package com.ezlinker.app;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;

/**
 * @program: ezlinker
 * @description: api
 * @author: wangwenhai
 * @create: 2019-11-05 11:50
 **/
public class Api extends ZeroArgFunction {

    @Override
    public LuaValue call() {
        return valueOf("API");
    }
}
