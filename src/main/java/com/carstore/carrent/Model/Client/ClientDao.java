package com.carstore.carrent.Model.Client;

import com.carstore.carrent.Model.Car.Car;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.carstore.carrent.Model.Database.IDataService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class ClientDao extends BaseDaoImpl<Client, Long> implements IDataService<Client> {
    public ClientDao(ConnectionSource connectionSource, Class<Client> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
    @Override
    public Client getByString(String name) {
        throw new UnsupportedOperationException("Feature incomplete. Contact assistance.");
    }
    public List<Client> getActiveClients(){
        List<Client> activeClients = null;
        try{
            activeClients = super.queryForAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return activeClients;
    }
}
