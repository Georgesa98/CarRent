package com.carstore.carrent.Model.User;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.carstore.carrent.Model.Car.Car;
import com.carstore.carrent.Model.Database.DBConnection;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.carstore.carrent.Model.Database.IDataService;

import java.sql.SQLException;
import java.util.List;

public class UserDao extends BaseDaoImpl<User, Long> implements IDataService<User> {
    public UserDao(ConnectionSource connectionSource, Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
    @Override
    public User getByString(String name) {
        throw new UnsupportedOperationException();
    }
    public static boolean loginToApp(String username, String password) throws SQLException {
        UserDao dao = new UserDao(DBConnection.connectionSource, User.class);
        User user = dao.queryBuilder().where()
                .eq("username", username).queryForFirst();
        if (user != null){
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            return result.verified;
        }
        else {
            return false;
        }
    }
}
