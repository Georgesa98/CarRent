package com.carstore.carrent.Components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RentCardController {
    @FXML
    private Label rentEndDate;
    @FXML
    private Label payment;
    @FXML
    private Label carModel;
    @FXML
    private Label clientName;
    public void setData(String rentEndDate, double payment, String carModel, String clientName){
        this.rentEndDate.setText(rentEndDate);
        this.carModel.setText(carModel);
        this.payment.setText(Double.toString(payment));
        this.clientName.setText(clientName);
    }
}
