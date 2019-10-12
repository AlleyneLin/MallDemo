package com.alleyne.mall.dao.impl;

import com.alleyne.mall.dao.UserDao;
import com.alleyne.mall.domain.User;
import com.alleyne.mall.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public void userRegist(User user01) throws SQLException {
        String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
        Object[] params={user01.getUid(),user01.getUsername(),user01.getPassword(),user01.getName(),user01.getEmail(),
                user01.getTelephone(),user01.getBirthday(),user01.getSex(),user01.getState(),user01.getCode()};
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        queryRunner.update(sql, params);
    }
}
