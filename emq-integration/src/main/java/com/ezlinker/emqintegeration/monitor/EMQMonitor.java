package com.ezlinker.emqintegeration.monitor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @program: ezlinker
 * @description: EMQ监控接口
 * @author: wangwenhai
 * @create: 2019-11-21 14:52
 **/
public class EMQMonitor {
    private String appId;
    private String appSecret;
    private String hostUrl = "http://127.0.0.1:8080/api/v3";

    /**
     * @param appId     appId
     * @param appSecret appSecret
     */
    public EMQMonitor(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    /**
     * @param appId     appId
     * @param appSecret appSecret
     * @param hostUrl   URL地址
     */
    public EMQMonitor(String appId, String appSecret, String hostUrl) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.hostUrl = hostUrl;
    }

    /**
     * 获取集群连接信息
     *
     * @param page  页码
     * @param limit 条数
     * @return
     */
    public String getClusterConnections(Integer page, Integer limit) {
        String body = HttpRequest.get(StrUtil.format("{}{}?_page={}&_limit={}", hostUrl, EmqUrl.CONNECTIONS, page, limit))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取节点连接信息
     *
     * @param node  节点名称
     * @param page  页码
     * @param limit 条数
     * @return
     */
    public String getNodeConnections(String node, Integer page, Integer limit) {
        String body = HttpRequest.get(StrUtil.format("{}{}{}{}?_page={}&_limit={}", hostUrl, "/nodes/", node, EmqUrl.CONNECTIONS, page, limit))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取集群指定客户端连接信息
     *
     * @param clientId 客户端名称
     * @return
     */
    public String getClusterClientConnections(String clientId) {
        String body = HttpRequest.get(StrUtil.format("{}{}{}", hostUrl, EmqUrl.CONNECTIONS, clientId))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取节点指定客户端连接信息
     *
     * @param clientId 客户端名称
     * @return
     */
    public String getNodeClientConnections(String node, String clientId) {
        String body = HttpRequest.get(StrUtil.format("{}{}{}{}{}", hostUrl, "/nodes/", node, EmqUrl.CONNECTIONS, clientId))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 断开指定连接
     *
     * @param clientId 客户端ID
     * @return
     */
    public String deleteConnections(String clientId) {
        String body = HttpRequest.delete(StrUtil.format("{}{}{}", hostUrl, EmqUrl.CONNECTIONS, clientId))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取集群收发报文统计
     *
     * @return
     */
    public String getClusterMetrics() {
        String body = HttpRequest.get(StrUtil.format("{}{}", hostUrl, EmqUrl.METRICS))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取节点收发报文统计
     *
     * @param node 节点名称
     * @return
     */
    public String getNodeMetrics(String node) {
        String body = HttpRequest.get(StrUtil.format("{}{}{}{}", hostUrl, "/nodes/", node, EmqUrl.METRICS))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取集群连接会话统计
     *
     * @return
     */
    public String getClusterStats() {
        String body = HttpRequest.get(StrUtil.format("{}{}", hostUrl, EmqUrl.STATS))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取节点连接会话统计
     *
     * @param node 节点名称
     * @return
     */
    public String getNodeStats(String node) {
        String body = HttpRequest.get(StrUtil.format("{}{}{}{}", hostUrl, "/nodes/", node, EmqUrl.STATS))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取集群当前告警信息
     *
     * @return
     */
    public String getClusterAlarmsPresent() {
        String body = HttpRequest.get(StrUtil.format("{}{}", hostUrl, EmqUrl.ALARMS_PRESENT))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取节点当前告警信息
     *
     * @param node 节点名称
     * @return
     */
    public String getNodeAlarmsPresent(String node) {
        String body = HttpRequest.get(StrUtil.format("{}{}/{}", hostUrl, EmqUrl.ALARMS_PRESENT, node))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取集群历史告警信息
     *
     * @return
     */
    public String getClusterAlarmsHistory() {
        String body = HttpRequest.get(StrUtil.format("{}{}", hostUrl, EmqUrl.ALARMS_HISTORY))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取节点历史告警信息
     *
     * @param node 节点名称
     * @return
     */
    public String getNodeAlarmsHistory(String node) {
        String body = HttpRequest.get(StrUtil.format("{}{}/{}", hostUrl, EmqUrl.ALARMS_HISTORY, node))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 获取黑名单列表
     *
     * @return
     */
    public String getBanned() {
        String body = HttpRequest.get(StrUtil.format("{}{}", hostUrl, EmqUrl.BANNED))
                .basicAuth(appId, appSecret)
                .execute()
                .body();
        return body;
    }

    /**
     * 创建黑名单
     *
     * @param banned 创建参数，参见官方文档 {@see <a href="https://docs.emqx.io/broker/v3/cn/rest.html#id45">EMQX</a>}
     * @return
     */
    public String createBanned(@NonNull Banned banned) {
        String body = HttpRequest.post(StrUtil.format("{}{}", hostUrl, EmqUrl.BANNED))
                .basicAuth(appId, appSecret)
                .body(JSONUtil.toJsonStr(banned))
                .execute()
                .body();
        return body;
    }

    /**
     * 删除指定黑名单 参见官方文档 {@see <a href="https://docs.emqx.io/broker/v3/cn/rest.html#id46">EMQX</a>}
     *
     * @param who 黑名单
     * @param as  clientId
     * @return
     */
    public String deleteBanned(String who, String as) {
        String body = HttpRequest.delete(StrUtil.format("{}{}{}?as={}", hostUrl, EmqUrl.BANNED, who, as))
                .basicAuth(appId, appSecret)
                .execute().body();
        return body;
    }


    private static class EmqUrl {
        /**
         * 获取连接信息
         */
        private static final String CONNECTIONS = "/connections/";
        /**
         * 收发报文统计
         */
        private static final String METRICS = "/metrics/";
        /**
         * 连接会话统计
         */
        private static final String STATS = "/stats/";
        /**
         * 当前告警信息
         */
        private static final String ALARMS_PRESENT = "/alarms/present";
        /**
         * 历史告警信息
         */
        private static final String ALARMS_HISTORY = "/alarms/history";
        /**
         * 黑名单
         */
        private static final String BANNED = "/banned/";
    }

    @Data
    private static class Banned implements Serializable {
        private static final long serialVersionUID = 7413237308400543305L;
        private String who;
        private String as;
        private String reason;
        private String desc;
        private Long until;
    }

}
