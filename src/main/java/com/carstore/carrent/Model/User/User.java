package com.carstore.carrent.Model.User;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "users", daoClass = UserDao.class)
public class User {
    @DatabaseField(generatedId = true)
    private long Id;
    @DatabaseField(columnName = "fullname")
    private String fullName;
    @DatabaseField(columnName = "username", unique = true)
    private String username;

    @DatabaseField(columnName = "password")

    private String password;

    public User() {
    }

    public long getId() {
        return Id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        this.password = hashedPassword;
    }

    public String getPassword() {
        return password;
    }
}
