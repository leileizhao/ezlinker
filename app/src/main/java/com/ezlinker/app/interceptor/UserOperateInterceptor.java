package com.ezlinker.app.interceptor;

import com.ezlinker.app.modules.user.model.UserDetail;
import com.ezlinker.app.utils.UserTokenUtil;
import com.ezlinker.common.exception.XException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: ezlinker
 * @description: 认证拦截器
 * @author: wangwenhai
 * @create: 2019-11-07 10:02
 **/
public class UserOperateInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("来自:" + getIpAddress(request));

        if (!hasToken(request)) {
            throw new XException(401, "Require token", "Token缺失");
        }

        /**
         * 如果匹配到了，开始检查权限
         */
        if (handler instanceof HandlerMethod) {
            String httpMethod = request.getMethod().toUpperCase();
            UserDetail userDetail = UserTokenUtil.parse(request.getHeader("token"));
            if (userDetail.getPermissions().size() < 1) {
                throw new XException(402, "No permission", "没有权限");
            }
            String path = request.getServletPath();
            List<String> permissions = new ArrayList<>();

            for (String resource : userDetail.getPermissions()) {
                String[] rpr = resource.split("::");
                // 先判断请求方法是否匹配
                if (rpr.length == 2) {
                    if (!rpr[0].equals("ALL")) {
                        List<String> methods = Arrays.asList(rpr[0].replace("[", "").replace("]", "").split(","));
                        if (!methods.contains(httpMethod)) {
                            throw new XException(402, "No permission", "没有权限");
                        }
                    }
                    // 然后判断请求路径是否匹配
                    permissions.add(rpr[1]);
                }
            }
            //获取请求的地址
            if (permissions.contains(path)) {
                // 虽然请求匹配到了，但是方法不一定匹配

                return true;
            } else {
                throw new XException(402, "No permission", "没有权限");
            }


        } else {
            throw new XException(404, "Resource not found", "资源不存在");
        }
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }

    /**
     * 是否包含Token
     *
     * @param request
     * @return
     */
    private boolean hasToken(HttpServletRequest request) {

        String token = request.getHeader("token");
        return token != null && token.length() >= 20;

    }
}
