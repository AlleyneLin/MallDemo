package com.alleyne.mall.dao;

import com.alleyne.mall.domain.User;

import java.sql.SQLException;

public interface UserDao {
    void userRegist(User user)throws SQLException;
    User userActive(String code)throws SQLException;
    void updateUser(User user)throws SQLException;
    User userLogin(User user)throws SQLException;
}
