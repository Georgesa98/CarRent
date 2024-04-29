package com.carstore.carrent;

import com.carstore.carrent.Model.Car.Car;
import com.carstore.carrent.Model.Car.CarDao;
import com.carstore.carrent.Model.Client.Client;
import com.carstore.carrent.Model.Client.ClientDao;
import com.carstore.carrent.Model.Database.DBConnection;
import com.carstore.carrent.Model.User.User;
import com.carstore.carrent.Model.User.UserDao;
import com.carstore.carrent.Utils.ExpiryTaskSchedular;
import com.j256.ormlite.stmt.Where;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 320);
        DBConnection.connectToDb();
        stage.setTitle("Car Rent App");
        stage.setScene(scene);
        stage.show();
        ExpiryTaskSchedular expiryTaskSchedular = new ExpiryTaskSchedular();
        Timer timer = new Timer();
        timer.schedule(expiryTaskSchedular, 1000);
    }

    public static void main(String[] args) {
        launch();
    }
}