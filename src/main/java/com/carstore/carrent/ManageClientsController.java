package com.carstore.carrent;

import com.carstore.carrent.Model.Car.Car;
import com.carstore.carrent.Model.Car.CarDao;
import com.carstore.carrent.Model.Client.Client;
import com.carstore.carrent.Model.Database.DBConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ManageClientsController {
    @FXML
    private Button addCustomer;

    @FXML
    private ChoiceBox<Car> availableCars;
    @FXML
    private TableView<Client> customerTable;

    @FXML
    private TextField fullnameField;

    @FXML
    private TextField paymentField;
    @FXML
    private TableColumn<Client, String> carCol;
    @FXML
    private TableColumn<Client, String> fullnameCol;
    @FXML
    private TableColumn<Client, String> paymentCol;
    @FXML
    private TableColumn<Client, String> rentDateCol;
    private ObservableList<Client> clients;
    void addCustomer(ActionEvent event) {
    }
    public void initialize() throws SQLException {
        availableCars.getItems().addAll(getAvailableCars());
        fullnameCol.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("payment"));
        rentDateCol.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        carCol.setCellValueFactory(new PropertyValueFactory<>("car"));
    }
    public List<Car> getAvailableCars() throws SQLException {
        CarDao dao = new CarDao(DBConnection.connectionSource, Car.class);
        List<Car> availableCars = dao.queryBuilder().where()
                .eq("isAvailable", true).query();
        return availableCars;
    }
}
