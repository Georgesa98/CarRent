package com.carstore.carrent.Model.Database;

import com.carstore.carrent.Model.Client.Client;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.carstore.carrent.Model.Car.Car;
import com.carstore.carrent.Model.User.User;

import java.sql.SQLException;

public class DBConnection{

    public static JdbcPooledConnectionSource connectionSource = null;
    public DBConnection() {

    }
    public static void connectToDb(){
        try {
            connectionSource =  new JdbcPooledConnectionSource("jdbc:mariadb://localhost:3306/carstore", "root", "zaq321xsw");
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, Car.class);
            TableUtils.createTableIfNotExists(connectionSource, Client.class);


            System.out.println("connected successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
