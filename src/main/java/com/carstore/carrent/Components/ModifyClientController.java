package com.carstore.carrent.Components;

import com.carstore.carrent.ManageCarsController;
import com.carstore.carrent.ManageClientsController;
import com.carstore.carrent.Model.Car.Car;
import com.carstore.carrent.Model.Car.CarDao;
import com.carstore.carrent.Model.Client.Client;
import com.carstore.carrent.Model.Client.ClientDao;
import com.carstore.carrent.Model.Database.DBConnection;
import com.carstore.carrent.Utils.DateConverter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ModifyClientController {
    @FXML
    private ChoiceBox<Car> availableCars;

    @FXML
    private TextField fullnameField;

    @FXML
    private TextField paymentField;
    @FXML
    private DatePicker rentEndDateField;
    private Client currClient;
    private ManageClientsController manageClientsController;
    public void setSelectedClient(Client client){
        this.currClient = client;
        availableCars.setValue(client.getCar());
        fullnameField.setText(client.getFullname());
        paymentField.setText(Double.toString(client.getPayment()));
        rentEndDateField.setValue(DateConverter.dateToLocalDateConverter(client.getRentDate()));

    }
    public void initialize() throws SQLException {
        availableCars.getItems().clear();
        availableCars.getItems().addAll(getAvailableCars());
    }
    public void setManageClientController(ManageClientsController manageClientController){
        this.manageClientsController = manageClientController;
    }
    public void updateClient() throws SQLException {
        ClientDao dao = new ClientDao(DBConnection.connectionSource, Client.class);
        CarDao carDao = new CarDao(DBConnection.connectionSource, Car.class);
        Client newData = new Client();
        newData.setId(currClient.getId());
        newData.setRentDate(DateConverter.localDateToDateConverter(rentEndDateField.getValue()));
        newData.setFullname(fullnameField.getText());
        newData.setCar(availableCars.getValue());
        newData.setPayment(Double.parseDouble(paymentField.getText()));
        if(currClient.getCar() != availableCars.getValue()){
            Car newCarData = availableCars.getValue();
            Car oldCarData = currClient.getCar();
            oldCarData.setAvailable(true);
            newCarData.setAvailable(false);
            carDao.update(newCarData);
            carDao.update(oldCarData);
        }
        dao.update(newData);
        manageClientsController.initialize();
        Stage stage = (Stage) paymentField.getScene().getWindow();
        stage.close();
    }
    public void deleteClient() throws SQLException {
        ClientDao dao = new ClientDao(DBConnection.connectionSource, Client.class);
        CarDao carDao = new CarDao(DBConnection.connectionSource, Car.class);
        Car newCarData = currClient.getCar();
        newCarData.setAvailable(true);
        carDao.update(newCarData);
        dao.delete(currClient);
        manageClientsController.initialize();
        Stage stage = (Stage) paymentField.getScene().getWindow();
        stage.close();
    }
    public void closeWindow(){
        Stage stage = (Stage) paymentField.getScene().getWindow();
        stage.close();
    }
    public List<Car> getAvailableCars() throws SQLException {
        CarDao dao = new CarDao(DBConnection.connectionSource, Car.class);
        List<Car> availableCars = dao.queryBuilder().where()
                .eq("isAvailable", true).query();
        return availableCars;
    }
}
