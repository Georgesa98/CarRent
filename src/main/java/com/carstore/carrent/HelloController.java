package com.carstore.carrent;

import com.carstore.carrent.Model.Database.DBConnection;
import com.carstore.carrent.Model.User.User;
import com.carstore.carrent.Model.User.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
@FXML
private Label loginLabel;
@FXML
private Button loginButton;
@FXML
private TextField username;
@FXML
private TextField password;

@FXML
public void login(){
    try {
        String username = this.username.getText();
        String password = this.password.getText();
        boolean loginCheck = UserDao.loginToApp(username, password);
        if(loginCheck){
            Stage currStage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Components/MainWindow.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setMinHeight(720);
            stage.setMinWidth(1280);
            stage.setResizable(false);
            currStage.close();
            stage.show();
        }
        else {
            loginLabel.setText("username or password are incorrect");
        }
    }
    catch(Exception e){
        System.err.println(e.getMessage());
    }
}
}