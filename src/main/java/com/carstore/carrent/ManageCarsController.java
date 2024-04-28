package com.carstore.carrent;

import com.carstore.carrent.Components.ModifyCarController;
import com.carstore.carrent.Model.Car.Car;
import com.carstore.carrent.Model.Car.CarDao;
import com.carstore.carrent.Model.Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;


public class ManageCarsController {
    @FXML
    private TableView carTable;

    @FXML
    private TableColumn<Car, String > colorCol;

    @FXML
    private TableColumn<Car, String> manufacturerCol;

    @FXML
    private TableColumn<Car, String> modelCol;

    @FXML
    private TableColumn<Car, Double> rentPriceCol;

    @FXML
    private TableColumn<Car, String> yearOfManufactureCol;
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
    private ObservableList<Car> cars;
    public void initialize() throws SQLException {
        CarDao dao = new CarDao(DBConnection.connectionSource, Car.class);
        cars = FXCollections.observableArrayList(dao.queryForAll());
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        manufacturerCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        rentPriceCol.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        yearOfManufactureCol.setCellValueFactory(new PropertyValueFactory<>("yearOfManufacture"));
        carTable.setItems(cars);

        carTable.setRowFactory(
                car -> {
                    TableRow<Car> row = new TableRow<>();
                    row.setOnMouseClicked(mouseEvent -> {
                        if (mouseEvent.getClickCount() == 2 && (! row.isEmpty())){
                            Car rowData = row.getItem();
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
    public void addCar(ActionEvent event) throws SQLException {
        CarDao dao = new CarDao(DBConnection.connectionSource, Car.class);
        String manufacturer = manufacturerField.getText();
        String model = modelField.getText();
        double rentPrice = Double.parseDouble(rentPriceField.getText());
        String color = colorField.getText();
        String yearOfManufacturer = yearOfManufacturerField.getText();
        Car car = new Car();
        car.setColor(color);
        car.setRentPrice(rentPrice);
        car.setManufacturer(manufacturer);
        car.setModel(model);
        car.setYearOfManufacture(yearOfManufacturer);
        car.setAvailable(true);
        dao.create(car);
        cars.add(car);
        colorField.setText("");
        manufacturerField.setText("");
        modelField.setText("");
        rentPriceField.setText("");
        yearOfManufacturerField.setText("");
    }
    public void openEditMenu(Car selectedCar) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Components/ModifyCar.fxml"));
        Parent window = fxmlLoader.load();
        ModifyCarController controller = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(window));
        controller.setSelectedCar(selectedCar);
        controller.setManageCarController(this);
        stage.show();
    }
}
