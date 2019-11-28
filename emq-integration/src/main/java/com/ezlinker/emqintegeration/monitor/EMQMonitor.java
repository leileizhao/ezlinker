package com.ezlinker.emqintegeration.monitor;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

/**
 * @program: ezlinker
 * @description: EMQ监控接口
 * @author: wangwenhai
 * @create: 2019-11-21 14:52
 **/
public class EMQMonitor {
    private static final String APPID = "";
    private static final String SECRET = "";
    private static final String HTTP_URL = "http://127.0.0.1:8080/api/v3/";

    /**
     * 获取集群内的节点
     *
     * @return
     */
    public String getBrokers() {
        String result = HttpUtil.get(HTTP_URL + "api/v3/brokers/");
        return JSONUtil.parseObj(result).get("data").toString();
    }

}
