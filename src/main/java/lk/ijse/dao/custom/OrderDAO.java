package lk.ijse.dao.custom;

import lk.ijse.entity.Order;
import java.util.List;

public interface OrderDAO {
    void saveOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    void deleteOrder(Order order);
}