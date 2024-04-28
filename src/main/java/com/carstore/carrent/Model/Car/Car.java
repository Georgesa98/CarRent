package com.carstore.carrent.Model.Car;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "cars", daoClass = CarDao.class)
public class Car {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "manufacturer")
    private String manufacturer;
    @DatabaseField(columnName = "model")
    private String model;
    @DatabaseField(columnName = "color")
    private String color;
    @DatabaseField(columnName = "yearOfManufacture")
    private String yearOfManufacture;
    @DatabaseField(columnName = "rentPrice")
    private double rentPrice;
    @DatabaseField(columnName = "isAvailable")
    private boolean isAvailable;
    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return  manufacturer + " " +  model;
    }
}
