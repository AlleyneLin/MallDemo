package com.alleyne.mall.web.servlet;

import com.alleyne.mall.domain.User;
import com.alleyne.mall.service.UserService;
import com.alleyne.mall.service.impl.UserServiceImpl;
import com.alleyne.mall.utils.BeanFactory;
import com.alleyne.mall.utils.CookieUtils;
import com.alleyne.mall.utils.MyBeanUtills;
import com.alleyne.mall.utils.UUIDUtils;
import com.alleyne.mall.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {
    private UserService userService = (UserService) BeanFactory.createObject("UserService");
    public String regist(HttpServletRequest reques, HttpServletResponse response) throws Exception{
        //封装数据，
        User user = MyBeanUtills.populate(User.class, reques.getParameterMap());
        user.setUid(UUIDUtils.getId());
        user.setCode(UUIDUtils.getUUID64());    //激活码
        user.setState(0);                       //默认未激活

        //处理注册
        UserService userService = new UserServiceImpl();
        userService.userRegist(user);

        //提示
        reques.setAttribute("msg", "注册成功，请登录邮箱激活后登录");
        //注册成功跳转登录
        return "/jsp/login.jsp";
    }

    public String active(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //服务端获取到激活码,和数据库中已经存在的激活码对比,如果存在,激活成功,更改用户激活状态1,转发到登录页面,提示:激活成功,请登录.
        String code = request.getParameter("code");
        //调用业务层功能:根据激活码查询用户 select * from user where code=?
        User user = userService.userActive(code);
        //如果用户不为空,即激活码正确的,则激活
        if (user != null){
            user.setState(1);
            user.setCode("");
            userService.updateUser(user);
            //转发到登录页面,提示:激活成功,请登录
            request.setAttribute("msg", "用户激活成功,请登录");
        }else {
            request.setAttribute("msg", "用户激活失败");
        }
        return "/jsp/login.jsp";
    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Cookie ck=CookieUtils.getCookieByName("remUser", request.getCookies());
        if(null!=ck){
            request.setAttribute("remUser", ck.getValue());
        }
        return "/jsp/login.jsp";
    }

    public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //接受用户名和密码
        User user1 = MyBeanUtills.populate(User.class, request.getParameterMap());
        //调用业务层登录功能
        User user2 = userService.userLogin(user1);
        if (user2 != null){
            request.getSession().setAttribute("loginUser", user2);
            //在登录成功的基础上,判断用户是否选中自动登录复选框
            String autoLogin = request.getParameter("autoLogin");
            if ("yes".equals(autoLogin)){
                Cookie ck = new Cookie("autoLogin", user2.getName()+"#"+user2.getPassword());
                ck.setPath("/MallDemo");
                ck.setMaxAge(1314);
                response.addCookie(ck);
            }
            String remUser = request.getParameter("remUser");
            if("yes".equals(remUser)){
                Cookie ck = new Cookie("remUser", user2.getName());
                ck.setPath("/MallDemo");
                ck.setMaxAge(1314);
                response.addCookie(ck);
            }
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            request.setAttribute("msg", "用户名和密码不匹配");
            return "/jsp/login.jsp";
        }
        return null;
    }

    public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //用户退出,清空用户session
        request.getSession().invalidate();
        Cookie ck= CookieUtils.getCookieByName("autoLogin", request.getCookies());
        if(null!=ck){
            ck.setMaxAge(0);
            ck.setPath("/store_v4");
            response.addCookie(ck);
        }

        response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
        return null;
    }
}
