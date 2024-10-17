
package model;

import model.Order;
import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    List<Order> getOrders();
    void updateOrder(Order order);
    void deleteOrder(int orderNumber);
    Order getOrder(int orderNumber);
}




