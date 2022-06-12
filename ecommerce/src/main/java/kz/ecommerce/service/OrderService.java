package kz.ecommerce.service;

import kz.ecommerce.domain.Order;
import kz.ecommerce.domain.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Order getOrderById(Long orderId);

    List<Order> getAllOrders();

    Order postOrder(Order validOrder, Map<Long, Long> perfumesId);

    List<Order> getUserOrders(String email);

    List<OrderItem> getOrderItemsByOrderId(Long orderId);

    String deleteOrder(Long orderId);

}
