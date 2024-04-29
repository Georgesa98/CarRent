package com.carstore.carrent.Utils;

import com.carstore.carrent.Model.Car.Car;
import com.carstore.carrent.Model.Car.CarDao;
import com.carstore.carrent.Model.Client.Client;
import com.carstore.carrent.Model.Client.ClientDao;
import com.carstore.carrent.Model.Database.DBConnection;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class ExpiryTaskSchedular extends TimerTask {
    Date currTime;
    @Override
    public void run() {
        this.currTime = new Date();
        try {
            ClientDao dao = new ClientDao(DBConnection.connectionSource, Client.class);
            CarDao carDao = new CarDao(DBConnection.connectionSource, Car.class);
            List<Client> clients = dao.queryForAll();;
            for(Client client: clients){
                if (currTime.compareTo(client.getRentDate()) > 0){
                    client.getCar().setAvailable(true);
                    carDao.update(client.getCar());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
