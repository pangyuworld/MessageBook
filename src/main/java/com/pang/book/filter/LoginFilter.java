package com.pang.book.filter;

import com.pang.book.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebFilter(filterName = "loginFilter",urlPatterns = {"/message/*","/common/*","/user/*"})
public class LoginFilter implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
//        System.out.println("初始化");
    }

    /**
     * 拦截未登录的用户进行操作
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        User user= (User) request.getSession().getAttribute("user");
        if (user == null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/login");
        } else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
