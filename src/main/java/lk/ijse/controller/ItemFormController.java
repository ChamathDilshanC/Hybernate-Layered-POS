package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dto.ItemDTO;

public class ItemFormController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    ItemBO itemBO = (ItemBO) BOFactory.getBO(BOFactory.BOType.ITEM);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ItemDTO itemDTO = new ItemDTO(Integer.parseInt(txtId.getText()), txtModel.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText()));
        itemBO.delete(itemDTO);
        Alert alert = new Alert(Alert.AlertType.INFORMATION , "Deleted");
        alert.show();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
         try{
             ItemDTO itemDTO = new ItemDTO(Integer.parseInt(txtId.getText()), txtModel.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText()));
             itemBO.save(itemDTO);
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved");
             alert.show();
         }catch (Exception e){
             Alert alert = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
             alert.show();
         }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ItemDTO itemDTO = new ItemDTO(Integer.parseInt(txtId.getText()), txtModel.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText()));
        itemBO.update(itemDTO);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Updated");
        alert.show();

    }

    private void getalldetailsfromid() {
        ItemDTO itemDTO = itemBO.get(Integer.parseInt(txtId.getText()));
        txtModel.setText(itemDTO.getModel());
        txtPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
        txtQty.setText(String.valueOf(itemDTO.getQty()));


    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtModel.requestFocus();
    }

    @FXML
    void txtModelOnAction(ActionEvent event) {
        txtPrice.requestFocus();
    }

    @FXML
    void txtPriceOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    public void btnSearchOnActioneOnAction(ActionEvent actionEvent) {
        getalldetailsfromid();
    }
}
