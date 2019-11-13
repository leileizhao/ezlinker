package com.ezlinker.app.utils;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: ezlinker
 * @description:
 * @author: wangwenhai
 * @create: 2019-11-13 09:52
 **/
public class T {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("POST");
        list.add("GET");
        //
        List<String> list1 = Collections.singletonList("POST, GET");
        System.out.println(list.toString());
        System.out.println(list1);
        System.out.println(JSONArray.toJSONString(list));
    }
}
