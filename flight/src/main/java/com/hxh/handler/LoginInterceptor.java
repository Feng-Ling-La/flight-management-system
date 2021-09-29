package com.hxh.handler;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        /**
         * 从session中拿去 名为 "loginAdmin" 的一个对象，该对象类型为Object类型，如果session中没
         * 中各对象，就返回null。
         *
         */
        if (request.getSession().getAttribute("loginAdmin") == null) {
            //重定向到 admin界面，即：再一次发起 "/admin"请求。
            response.sendRedirect("/admin");
            //不放行，表示，"到此为止"，再不往下执行
            return false;
        }
        //放行：放行原因是：从session中拿出了名为 "loginAdmin" 的对象。
        return true;
    }
}
