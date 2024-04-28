package com.carstore.carrent.Model.Car;


import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.carstore.carrent.Model.Database.IDataService;

import java.sql.SQLException;

public class CarDao extends BaseDaoImpl<Car, Long> implements IDataService<Car> {
    public CarDao(ConnectionSource connectionSource, Class<Car> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public Car getByString(String name) {
        throw new UnsupportedOperationException("Feature incomplete. Contact assistance.");
    }
}
