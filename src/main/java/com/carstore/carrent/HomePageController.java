package com.carstore.carrent;

import com.carstore.carrent.Components.RentCardController;
import com.carstore.carrent.Model.Car.Car;
import com.carstore.carrent.Model.Car.CarDao;
import com.carstore.carrent.Model.Client.Client;
import com.carstore.carrent.Model.Client.ClientDao;
import com.carstore.carrent.Model.Database.DBConnection;
import com.carstore.carrent.Utils.DateConverter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.views.DocumentView;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomePageController {
@FXML
private GridPane onGoingRents;
    @FXML
    private Label monthlyIncome;

    @FXML
    private Label noOfCarsOwned;

    @FXML
    private Label noOfCarsRented;
private List<Client> activeClients;
public void initialize(){
    try{
        ClientDao dao = new ClientDao(DBConnection.connectionSource, Client.class);
        activeClients = new ArrayList<>(dao.getActiveClients());
        int columns = 0;
        int rows = 1;
        for(Client client: activeClients){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Components/RentCards.fxml"));
            VBox childNode = fxmlLoader.load();
            RentCardController rentCardController = fxmlLoader.getController();
            rentCardController.setData(client.getRentDate(), client.getPayment(), client.getCar().getModel(), client.getFullname());
            if (columns == 4){
                columns = 0;
                ++rows;
            }
            onGoingRents.add(childNode, columns++, rows);
            noOfCarsOwned.setText(Long.toString(getNoOfCars()));
            noOfCarsRented.setText(Long.toString(getNoOfRentedCars()));
            monthlyIncome.setText(Double.toString(getIncomeThisMonth()) + "$");
        }

    }
    catch (Exception e){
        e.printStackTrace();
    }
}
public long getNoOfCars() throws SQLException {
    CarDao dao = new CarDao(DBConnection.connectionSource, Car.class);
    return dao.queryForAll().stream().count();
}
public double getIncomeThisMonth() throws SQLException {
    LocalDate today = LocalDate.now();
    double income = 0;
    int year = today.getYear();
    int month = today.getMonthValue();
    Date x = DateConverter.localDateToDateConverter(LocalDate.of(year, month, 1));
    Date y = DateConverter.localDateToDateConverter(LocalDate.of(year, month, today.getDayOfMonth() + 1));
    ClientDao dao = new ClientDao(DBConnection.connectionSource, Client.class);
    List<Client> clients = dao.queryBuilder().where()
            .between("contractDate",
                    DateConverter.localDateToDateConverter(LocalDate.of(year, month, 1)),
                    DateConverter.localDateToDateConverter(LocalDate.of(year, month, today.getDayOfMonth() + 1))).query();
    for (Client client: clients){
        income += client.getPayment();
    }
    return income;
}
public long getNoOfRentedCars() throws SQLException {
    CarDao dao = new CarDao(DBConnection.connectionSource, Car.class);
    return dao.queryBuilder().where()
            .eq("isAvailable", false).countOf();
}

}
