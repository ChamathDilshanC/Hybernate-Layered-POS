package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private AnchorPane changePane;

    public void initialize() {
        try {
            changePane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/customer_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCustomer(ActionEvent event) {
        try {
            changePane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/customer_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItem(ActionEvent event) {
        try {
            changePane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/Item_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnOrder(ActionEvent event) {
        try {
            changePane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/OrderForm.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
