package kz.ecommerce.service.impl;

import kz.ecommerce.domain.Order;
import kz.ecommerce.domain.OrderItem;
import kz.ecommerce.domain.Perfume;
import kz.ecommerce.dto.perfume.PerfumeResponse;
import kz.ecommerce.exception.ApiRequestException;
import kz.ecommerce.repository.OrderItemRepository;
import kz.ecommerce.repository.OrderRepository;
import kz.ecommerce.repository.PerfumeRepository;
import kz.ecommerce.service.OrderService;
import kz.ecommerce.service.email.MailSender;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PerfumeRepository perfumeRepository;
    private final MailSender mailSender;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, PerfumeRepository perfumeRepository, MailSender mailSender) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.perfumeRepository = perfumeRepository;
        this.mailSender = mailSender;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ApiRequestException("Order not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByIdAsc();
    }

    @Override
    @Transactional
    public Order postOrder(Order validOrder, Map<Long, Long> perfumesId) {
        Order order = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();

        for (Map.Entry<Long, Long> entry : perfumesId.entrySet()) {
            Perfume perfume = perfumeRepository.findById(entry.getKey())
                    .orElseThrow(() -> new  ApiRequestException("Perfume not found", HttpStatus.NOT_FOUND));
            OrderItem orderItem = new OrderItem();
            orderItem.setPerfume(perfume);
            orderItem.setAmount((perfume.getPrice() * entry.getValue()));
            orderItem.setQuantity(entry.getValue());
            orderItemList.add(orderItem);

            orderItemRepository.save(orderItem);
        }

        order.getOrderItems().addAll(orderItemList);
        order.setTotalPrice(validOrder.getTotalPrice());
        order.setFirstName(validOrder.getFirstName());
        order.setLastName(validOrder.getLastName());
        order.setCity(validOrder.getCity());
        order.setAddress(validOrder.getAddress());
        order.setPostIndex(validOrder.getPostIndex());
        order.setEmail(validOrder.getEmail());
        order.setPhoneNumber(validOrder.getPhoneNumber());
        orderRepository.save(order);

        String subject = "Order #" + order.getId();
        String template = "order-template";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("order", order);
        mailSender.sendMessageHtml(order.getEmail(), subject, template, attributes);

        return order;
    }

    @Override
    public List<Order> getUserOrders(String email) {
        return orderRepository.findOrderByEmail(email);
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        Order order = getOrderById(orderId);
        return order.getOrderItems();
    }

    @Override
    @Transactional
    public String deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApiRequestException("Order not found.", HttpStatus.NOT_FOUND));
        orderRepository.delete(order);
        return "Order deleted successfully";
    }
}
