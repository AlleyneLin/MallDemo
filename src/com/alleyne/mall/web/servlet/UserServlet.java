package com.alleyne.mall.web.servlet;

import com.alleyne.mall.domain.User;
import com.alleyne.mall.service.UserService;
import com.alleyne.mall.service.impl.UserServiceImpl;
import com.alleyne.mall.utils.MyBeanUtills;
import com.alleyne.mall.utils.UUIDUtils;
import com.alleyne.mall.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {

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


}
