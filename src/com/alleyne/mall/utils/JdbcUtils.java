package com.alleyne.mall.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {
    private static DataSource dataSource = new ComboPooledDataSource("mall");

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


}
