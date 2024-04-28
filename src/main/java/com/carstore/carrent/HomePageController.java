package com.carstore.carrent;

import com.carstore.carrent.Components.RentCardController;
import com.carstore.carrent.Model.Client.Client;
import com.carstore.carrent.Model.Client.ClientDao;
import com.carstore.carrent.Model.Database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePageController {
@FXML
private GridPane onGoingRents;
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
            rentCardController.setData(client.getRentDate().toString(), client.getPayment(), client.getCar().getModel(), client.getFullname());
            if (columns == 4){
                columns = 0;
                ++rows;
            }
            onGoingRents.add(childNode, columns++, rows);
        }

    }
    catch (Exception e){
        e.printStackTrace();
    }
}


}
