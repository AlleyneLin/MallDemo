package com.alleyne.mall.service;

import com.alleyne.mall.domain.User;

import java.sql.SQLException;

public interface UserService {
    void userRegist(User user01)throws SQLException;

}
