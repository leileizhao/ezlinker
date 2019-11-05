package com.ezlinker.app;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

/**
 * @program: ezlinker
 * @description: api
 * @author: wangwenhai
 * @create: 2019-11-05 11:50
 **/
public class hyperbolic extends TwoArgFunction {

    public hyperbolic() {}

    @Override
    public LuaValue call(LuaValue name, LuaValue env) {
        LuaValue library = tableOf();
        library.set( "sinh", new sinh() );
        library.set( "cosh", new cosh() );
        env.set( "hyperbolic", library );
        return library;
    }

    static class sinh extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue x) {
            return LuaValue.valueOf(Math.sinh(x.checkdouble()));
        }
    }

    static class cosh extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue x) {
            return LuaValue.valueOf(Math.cosh(x.checkdouble()));
        }
    }
}
