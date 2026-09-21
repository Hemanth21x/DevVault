package com.devvault.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Runs before a request reaches the controller.
 * Blocks unauthenticated users from protected pages,
 * and non-admins from /admin/** pages.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession(false);
        Object user = (session == null) ? null : session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        // Admin-only area: roleId 3 = ADMIN
        String path = request.getRequestURI();
        if (path.contains("/admin")) {
            Integer roleId = (Integer) session.getAttribute("userRoleId");
            if (roleId == null || roleId != 3) {
                response.sendRedirect(request.getContextPath() + "/dashboard");
                return false;
            }
        }
        return true;
    }
}
