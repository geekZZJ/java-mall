package org.example.mall.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpSession;
import org.example.mall.common.ApiRestResponse;
import org.example.mall.common.Constant;
import org.example.mall.entity.Category;
import org.example.mall.entity.User;
import org.example.mall.exception.MallExceptionEnum;
import org.example.mall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;

public class AdminFilter implements Filter {
    @Autowired
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constant.MALL_USER);
        if (user == null) {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\"status\": 207, \"msg\": \"用户未登录\",\"data\": null}");
            out.flush();
            out.close();
            return;
        }
        Boolean adminRole = userService.checkAdminRole(user);
        if (adminRole) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\"status\": 209, \"msg\": \"非管理员\",\"data\": null}");
            out.flush();
            out.close();
         }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
