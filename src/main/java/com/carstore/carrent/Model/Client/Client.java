package com.carstore.carrent.Model.Client;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.carstore.carrent.Model.Car.Car;

import java.time.LocalDate;
import java.util.Date;
@DatabaseTable(tableName = "clients", daoClass = ClientDao.class)
public class Client {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "fullname")
    private String fullname;
    @DatabaseField(columnName = "car", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true)
    private Car car;
    @DatabaseField(columnName = "rentDate", dataType = DataType.DATE)
    private Date rentDate;
    @DatabaseField(columnName = "payment")
    private double payment;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
