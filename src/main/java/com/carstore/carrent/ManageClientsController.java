package com.carstore.carrent;

import com.carstore.carrent.Components.ModifyClientController;
import com.carstore.carrent.Model.Car.Car;
import com.carstore.carrent.Model.Car.CarDao;
import com.carstore.carrent.Model.Client.Client;
import com.carstore.carrent.Model.Client.ClientDao;
import com.carstore.carrent.Model.Database.DBConnection;
import com.carstore.carrent.Utils.DateConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ManageClientsController {

    @FXML
    private ChoiceBox<Car> availableCars;
    @FXML
    private TableView<Client> customerTable;

    @FXML
    private TextField fullnameField;

    @FXML
    private TextField paymentField;
    @FXML
    private DatePicker rentEndDateField;
    @FXML
    private TableColumn<Client, String> carCol;
    @FXML
    private TableColumn<Client, String> fullnameCol;
    @FXML
    private TableColumn<Client, String> paymentCol;
    @FXML
    private TableColumn<Client, String> rentDateCol;
    private ObservableList<Client> clients;

    public void initialize() throws SQLException {
        ClientDao clientDao = new ClientDao(DBConnection.connectionSource, Client.class);
        clients = FXCollections.observableArrayList(clientDao.queryForAll());
        availableCars.getItems().clear();
        availableCars.getItems().addAll(getAvailableCars());
        fullnameCol.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("payment"));
        rentDateCol.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        carCol.setCellValueFactory(new PropertyValueFactory<>("car"));
        customerTable.setItems(clients);
        customerTable.setRowFactory(
                car -> {
                    TableRow<Client> row = new TableRow<>();
                    row.setOnMouseClicked(mouseEvent -> {
                        if (mouseEvent.getClickCount() == 2 && (! row.isEmpty())){
                            Client rowData = row.getItem();
                            try {
                                openEditMenu(rowData);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    return row;
                }
        );

    }

    private void openEditMenu(Client client) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Components/ModifyClient.fxml"));
        Parent window = fxmlLoader.load();
        ModifyClientController controller = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(window));
        controller.setSelectedClient(client);
        controller.setManageClientController(this);
        stage.show();
    }

    public List<Car> getAvailableCars() throws SQLException {
        CarDao dao = new CarDao(DBConnection.connectionSource, Car.class);
        List<Car> availableCars = dao.queryBuilder().where()
                .eq("isAvailable", true).query();
        return availableCars;
    }
    public void addCustomer() throws SQLException {
        ClientDao dao = new ClientDao(DBConnection.connectionSource, Client.class);
        CarDao carDao = new CarDao(DBConnection.connectionSource, Car.class);
        Date date = new Date();
        String fullName = fullnameField.getText();
        Car car = availableCars.getValue();
        double payment = Double.parseDouble(paymentField.getText());
        LocalDate rentEndDate = rentEndDateField.getValue();
        Client client = new Client();
        car.setAvailable(false);
        carDao.update(car);
        client.setFullname(fullName);
        client.setCar(car);
        client.setContractDate(date);
        client.setPayment(payment);
        client.setRentDate(DateConverter.localDateToDateConverter(rentEndDate));
        dao.create(client);
        clients.add(client);
        fullnameField.setText("");
        availableCars.setValue(null);
        paymentField.setText("");
        rentEndDateField.setValue(null);
        this.initialize();
    }
}
