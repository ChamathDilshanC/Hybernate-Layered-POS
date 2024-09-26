// OrderItemDTO.java
package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {
    private int customerId;
    private int itemId;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

}