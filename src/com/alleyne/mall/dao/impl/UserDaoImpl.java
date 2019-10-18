package com.alleyne.mall.dao.impl;

import com.alleyne.mall.dao.UserDao;
import com.alleyne.mall.domain.User;
import com.alleyne.mall.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

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

    @Override
    public User userActive(String code) throws SQLException {
        String sql = "SELECT * FROM USER WHERE code = ?";
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<User>(User.class), code);
    }

    @Override
    public void updateUser(User user01) throws SQLException {
        String sql = "UPDATE USER SET username= ? ,PASSWORD=? ,NAME =? ,email =? ,telephone =? , birthday =?  ,sex =? ,state= ? , CODE = ? WHERE uid=?";
        Object[] params={user01.getUsername(),user01.getPassword(),user01.getName(),user01.getEmail(),user01.getTelephone(),user01.getBirthday(),user01.getSex(),user01.getState(),user01.getCode(),user01.getUid()};
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        queryRunner.update(sql, params);
    }

    @Override
    public User userLogin(User user) throws SQLException {
        String sql = "select * from user where username = ? and password = ?";
        QueryRunner queryRunner =  new QueryRunner(JdbcUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<User>(User.class), user.getName(), user.getPassword());
    }
}
