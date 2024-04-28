package com.carstore.carrent.Model.Database;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public interface IDataService<T> extends Dao<T, Long> {
    T getByString(String name) throws SQLException;
}
