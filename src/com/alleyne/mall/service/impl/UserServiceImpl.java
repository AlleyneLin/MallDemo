package com.alleyne.mall.service.impl;

import com.alleyne.mall.dao.UserDao;
import com.alleyne.mall.domain.User;
import com.alleyne.mall.service.UserService;
import com.alleyne.mall.utils.BeanFactory;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDao userDao = (UserDao) BeanFactory.createObject("UserDao");
    @Override
    public void userRegist(User user01) throws SQLException {
        //保存用户
        userDao.userRegist(user01);
    }

    @Override
    public User userActive(String code) throws SQLException{
        User user = userDao.userActive(code);
        return user;
    }

    @Override
    public void updateUser(User user01) throws SQLException {
        userDao.updateUser(user01);
    }

    @Override
    public User userLogin(User user01) throws SQLException {
        return userDao.userLogin(user01);
    }


}
