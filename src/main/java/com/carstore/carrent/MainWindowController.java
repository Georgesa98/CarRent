package com.carstore.carrent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainWindowController {
    @FXML
    private AnchorPane MainFrame;
    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        this.MainFrame.getChildren().add(fxmlLoader.load());
    }
    public void navigateToCars() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageCars.fxml"));
        this.MainFrame.getChildren().clear();
        this.MainFrame.getChildren().add(fxmlLoader.load());
    }
    public void navigateToHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        this.MainFrame.getChildren().clear();
        this.MainFrame.getChildren().add(fxmlLoader.load());
    }
    public void navigateToClients() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ManageClients.fxml"));
        this.MainFrame.getChildren().clear();
        this.MainFrame.getChildren().add(fxmlLoader.load());
    }
}
