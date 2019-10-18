package com.alleyne.mall.web.filter;

import com.alleyne.mall.domain.User;
import com.alleyne.mall.utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutoLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("loginUser");
        if(user == null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        Cookie cookie = CookieUtils.getCookieByName("autoLogin", request.getCookies());
        if (cookie == null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


    }

    @Override
    public void destroy() {

    }
}
