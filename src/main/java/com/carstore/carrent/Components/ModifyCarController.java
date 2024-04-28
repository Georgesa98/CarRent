package com.carstore.carrent.Components;

import com.carstore.carrent.MainWindowController;
import com.carstore.carrent.ManageCarsController;
import com.carstore.carrent.Model.Car.Car;
import com.carstore.carrent.Model.Car.CarDao;
import com.carstore.carrent.Model.Database.DBConnection;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.SQLException;

public class ModifyCarController {
    private Car currCar;
    @FXML
    private TextField colorField;

    @FXML
    private TextField manufacturerField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField rentPriceField;

    @FXML
    private TextField yearOfManufacturerField;
    private ManageCarsController manageCarsController;

    public void setSelectedCar(Car selectedCar){
        this.currCar = selectedCar;
        colorField.setText(selectedCar.getColor());
        manufacturerField.setText(selectedCar.getManufacturer());
        modelField.setText(selectedCar.getModel());
        rentPriceField.setText(Double.toString(selectedCar.getRentPrice()));
        yearOfManufacturerField.setText(selectedCar.getYearOfManufacture());
    }
    public void updateCar() throws SQLException {
        CarDao dao = new CarDao(DBConnection.connectionSource, Car.class);
        Car newData = new Car();
        newData.setId(currCar.getId());
        newData.setModel(modelField.getText());
        newData.setYearOfManufacture(yearOfManufacturerField.getText());
        newData.setRentPrice(Double.parseDouble(rentPriceField.getText()));
        newData.setColor(colorField.getText());
        newData.setManufacturer(manufacturerField.getText());
        dao.update(newData);
        Stage currStage = (Stage) colorField.getScene().getWindow();
        currStage.close();
        updateView();
    }
    public void closeWindow(){
        Stage currStage = (Stage) colorField.getScene().getWindow();
        currStage.close();
    }
    public void deleteCar() throws SQLException {
        CarDao dao = new CarDao(DBConnection.connectionSource, Car.class);
        dao.delete(currCar);
        Stage currStage = (Stage) colorField.getScene().getWindow();
        currStage.close();
        updateView();
    }
    public void setManageCarController(ManageCarsController manageCarsController){
        this.manageCarsController = manageCarsController;
    }
    public void updateView(){
        ManageCarsController controller = manageCarsController;
        try {
            controller.initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
