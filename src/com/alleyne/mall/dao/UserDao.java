package com.alleyne.mall.dao;

import com.alleyne.mall.domain.User;

import java.sql.SQLException;

public interface UserDao {
    void userRegist(User user01)throws SQLException;

}
