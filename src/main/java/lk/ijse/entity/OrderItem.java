package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int customerId;
    private int itemId;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    public OrderItem(int customerId, int itemId, int qty, double unitPrice, double totalPrice) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.quantity = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }
}