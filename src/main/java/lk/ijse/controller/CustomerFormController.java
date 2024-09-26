package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDTO;

public class CustomerFormController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBO(BOFactory.BOType.CUSTOMER);

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(txtId.getText()),txtName.getText(),txtAddress.getText(),Integer.parseInt(txtTel.getText()));
        try {
            customerBO.save(customerDTO);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
            alert.show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(txtId.getText()),txtName.getText(),txtAddress.getText(),Integer.parseInt(txtTel.getText()));
        customerBO.delete(customerDTO);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted");
        alert.show();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(txtId.getText()),txtName.getText(),txtAddress.getText(),Integer.parseInt(txtTel.getText()));
        customerBO.update(customerDTO);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated");
        alert.show();
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtTel.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    void txtTelOnAction(ActionEvent event) {

    }


    public void btnSearchOnActioneOnAction(ActionEvent actionEvent) {
        getdetailsfromid();
    }

    private void getdetailsfromid() {
        CustomerDTO customerDTO = customerBO.get(Integer.parseInt(txtId.getText()));
        txtName.setText(customerDTO.getName());
        txtAddress.setText(customerDTO.getAddress());
        txtTel.setText(String.valueOf(customerDTO.getTel()));
    }
}
