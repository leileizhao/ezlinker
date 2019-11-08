package com.ezlinker.app.interceptor;

import com.ezlinker.app.utils.UserDetail;
import com.ezlinker.app.utils.UserTokenUtil;
import com.ezlinker.common.auth.RequireRole;
import com.ezlinker.common.exception.XException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: ezlinker
 * @description: 认证拦截器
 * @author: wangwenhai
 * @create: 2019-11-07 10:02
 **/
public class RoleInterceptor implements HandlerInterceptor {
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
            UserDetail userDetail = UserTokenUtil.parse(request.getHeader("token"));
            if (userDetail.getRoles().size() < 1) {
                throw new XException(402, "No permission", "没有权限");
            }
            // 提取用户的Role列表构建数组
            String[] userRoles = new String[userDetail.getRoles().size()];
            userDetail.getRoles().toArray(userRoles);

            /**
             * 首先检查类注解
             */
            Class controllerClass = ((HandlerMethod) handler).getBeanType();
            RequireRole requireRoleOnClass = (RequireRole) controllerClass.getAnnotation(RequireRole.class);
            if (requireRoleOnClass != null) {
                String[] requiredRoles = requireRoleOnClass.roles();
                if (checkRole(requiredRoles, userRoles)) {
                    return true;
                } else {
                    throw new XException(402, "No permission", "没有权限");
                }

            }
            /**
             *  如果类上没有注解，检查方法注解
             */
            else {
                Method method = ((HandlerMethod) handler).getMethod();
                RequireRole requireRoleOnMethod = method.getAnnotation(RequireRole.class);
                if (requireRoleOnMethod != null) {
                    String[] requiredRoles = requireRoleOnMethod.roles();
                    if (checkRole(requiredRoles, userRoles)) {
                        return true;
                    } else {
                        throw new XException(402, "No permission", "没有权限");
                    }

                } else {
                    /**
                     * 都没有匹配到，就是不做拦截
                     */
                    return true;
                }

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

    /**
     * 检查角色
     *
     * @param requireRoles
     * @param userRoles
     * @return
     */

    private static boolean checkRole(String[] requireRoles, String[] userRoles) {
        if (requireRoles.length == 0 || userRoles.length == 0) {
            return false;
        }

        List<String> compareRoles = new ArrayList<>(Arrays.asList(userRoles));
        int requiredRolesCount = requireRoles.length;
        int realRolesCount = 0;
        for (String role : requireRoles) {
            if (compareRoles.contains(role)) {
                realRolesCount += 1;
            }
        }
        return requiredRolesCount == realRolesCount;
    }

}
