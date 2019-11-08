package com.ezlinker.app.interceptor;

import com.ezlinker.app.utils.UserDetail;
import com.ezlinker.app.utils.UserTokenUtil;
import com.ezlinker.common.auth.RequirePermission;
import com.ezlinker.common.exception.XException;
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
 * @description: 权限拦截器
 * @author: wangwenhai
 * @create: 2019-11-08 11:02
 **/
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            UserDetail userDetail = UserTokenUtil.parse(request.getHeader("token"));
            if (userDetail.getPermissions().size() < 1) {
                throw new XException(402, "No permission", "没有权限");
            }
            // 提取用户的userPermissions列表构建数组
            String[] userPermissions = new String[userDetail.getPermissions().size()];
            userDetail.getRoles().toArray(userPermissions);

            RequirePermission requirePermissionOnMethod = method.getAnnotation(RequirePermission.class);
            if (requirePermissionOnMethod != null) {
                String[] requiredRoles = requirePermissionOnMethod.permissions();
                if (checkPermission(requiredRoles, userPermissions)) {
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
        } else {
            throw new XException(404, "Resource not found", "资源不存在");

        }

    }

    /**
     * 检查用户的权限
     *
     * @param requirePermission
     * @param userPermission
     * @return
     */
    private static boolean checkPermission(String[] requirePermission, String[] userPermission) {
        if (requirePermission.length == 0 || userPermission.length == 0) {
            return false;
        }

        List<String> compareRoles = new ArrayList<>(Arrays.asList(userPermission));
        int requiredRolesCount = requirePermission.length;
        int realRolesCount = 0;
        for (String role : requirePermission) {
            if (compareRoles.contains(role)) {
                realRolesCount += 1;
            }
        }
        return requiredRolesCount == realRolesCount;
    }

}
