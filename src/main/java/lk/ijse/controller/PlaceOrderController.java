package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Order;
import lk.ijse.entity.OrderItem;
import lk.ijse.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PlaceOrderController {

    public Label labelItemName;
    @FXML
    private TableView<OrderItem> tablePlaceOrder;

    @FXML
    private TableColumn<OrderItem, String> colCustomerId;

    @FXML
    private TableColumn<OrderItem, String> colItemId;

    @FXML
    private TableColumn<OrderItem, Integer> colQty;

    @FXML
    private TableColumn<OrderItem, Double> colUnitPrice;

    @FXML
    private TableColumn<OrderItem, Double> colTotalPrice;

    @FXML
    private ComboBox<Integer> comboCustomerId;

    @FXML
    private ComboBox<Integer> comboItemId;

    @FXML
    private TextField txtQuantity;

    @FXML
    private Label labelUnitPrice;

    @FXML
    private Label labelTotalPrice;

    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialize table columns
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        tablePlaceOrder.setItems(orderItems);

        // Initialize combo boxes
        loadAllCustomers();
        loadAllItems();

        // Add listeners
        comboItemId.setOnAction(e -> updateUnitPriceAndItemName());
        txtQuantity.textProperty().addListener((obs, oldVal, newVal) -> updateTotalPrice());
    }

    private void loadAllItems() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT i.itemId FROM Item i");
            ObservableList<Integer> itemIds = FXCollections.observableArrayList(query.list());
            comboItemId.setItems(itemIds);
        } finally {
            session.close();
        }
    }

    private void loadAllCustomers() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("SELECT c.customerId FROM Customer c");
            ObservableList<Integer> customerIds = FXCollections.observableArrayList(query.list());
            comboCustomerId.setItems(customerIds);
        } finally {
            session.close();
        }
    }

    private void updateUnitPriceAndItemName() {
        try {
            Integer itemId = comboItemId.getValue();
            if (itemId != null) {
                Session session = FactoryConfiguration.getInstance().getSession();
                try {
                    session.beginTransaction();
                    Query query = session.createQuery("SELECT i.unitPrice, i.model FROM Item i WHERE i.itemId = :itemId");
                    query.setParameter("itemId", itemId);
                    Object[] result = (Object[]) query.uniqueResult();
                    if (result != null) {
                        double unitPrice = (Double) result[0];
                        String itemName = (String) result[1];
                        labelUnitPrice.setText(String.format("%.2f", unitPrice));
                        labelItemName.setText(itemName);
                        updateTotalPrice();
                    } else {
                        labelUnitPrice.setText("0.00");
                        labelItemName.setText("Item not found");
                    }
                } finally {
                    session.close();
                }
            } else {
                labelUnitPrice.setText("0.00");
                labelItemName.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            labelUnitPrice.setText("0.00");
            labelItemName.setText("Error occurred");
        }
    }

    private void updateTotalPrice() {
        try {
            double unitPrice = Double.parseDouble(labelUnitPrice.getText());
            int quantity = txtQuantity.getText().isEmpty() ? 0 : Integer.parseInt(txtQuantity.getText());
            double totalPrice = unitPrice * quantity;
            labelTotalPrice.setText(String.format("%.2f", totalPrice));
        } catch (NumberFormatException e) {
            labelTotalPrice.setText("0.00");
        }
    }

    private void clear() {
        comboCustomerId.setValue(null);
        comboItemId.setValue(null);
        txtQuantity.clear();
        labelUnitPrice.setText("0.00");
        labelTotalPrice.setText("0.00");
        labelItemName.setText("");
    }

    @FXML
    private void btnCancelOrderOnAction() {
        orderItems.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order Cancelled");
        alert.show();
        clear();
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        int customerId = comboCustomerId.getValue();
        int itemId = comboItemId.getValue();
        int qty = Integer.parseInt(txtQuantity.getText());
        double unitPrice = Double.parseDouble(labelUnitPrice.getText());
        double totalPrice = Double.parseDouble(labelTotalPrice.getText());

        OrderItem orderItem = new OrderItem(customerId, itemId, qty, unitPrice, totalPrice);
        orderItems.add(orderItem);
    }

    @FXML
    private void btnPlaceOrderOnAction() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Create new Order
            Order order = new Order();
            order.setItems(new ArrayList<>(orderItems));
            order.setTotal(orderItems.stream().mapToDouble(OrderItem::getTotalPrice).sum());

            // Save Order and decrease item quantities
            for (OrderItem orderItem : orderItems) {
                Item item = session.get(Item.class, orderItem.getItemId());
                if (item != null && item.getQty() >= orderItem.getQuantity()) {
                    item.setQty(item.getQty() - orderItem.getQuantity());
                    session.update(item);
                    orderItem.setId(0); // Reset ID to allow Hibernate to generate a new one
                    session.save(orderItem);
                } else {
                    throw new Exception("Insufficient quantity for item: " + (item != null ? item.getModel() : "Unknown"));
                }
            }

            session.save(order);
            transaction.commit();

            orderItems.clear();
            tablePlaceOrder.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order Placed Successfully");
            alert.show();
            clear();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
            alert.show();
        } finally {
            session.close();
        }
    }
}