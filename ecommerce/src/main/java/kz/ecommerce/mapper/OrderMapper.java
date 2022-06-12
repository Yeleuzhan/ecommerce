package kz.ecommerce.mapper;

import kz.ecommerce.domain.Order;
import kz.ecommerce.dto.order.OrderItemResponse;
import kz.ecommerce.dto.order.OrderRequest;
import kz.ecommerce.dto.order.OrderResponse;
import kz.ecommerce.exception.InputFieldException;
import kz.ecommerce.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
public class OrderMapper {

    private final CommonMapper commonMapper;
    private final OrderService orderService;

    public OrderMapper(CommonMapper commonMapper, OrderService orderService) {
        this.commonMapper = commonMapper;
        this.orderService = orderService;
    }

    public List<OrderResponse> getAllOrders() {
        return commonMapper.convertToResponseList(orderService.getAllOrders(), OrderResponse.class);
    }

    public OrderResponse postOrder(OrderRequest orderRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        Order order = orderService.postOrder(commonMapper.convertToEntity(orderRequest, Order.class), orderRequest.getPerfumesId());
        return commonMapper.convertToResponse(order, OrderResponse.class);
    }

    public List<OrderResponse> getUserOrders(String email) {
        return commonMapper.convertToResponseList(orderService.getUserOrders(email), OrderResponse.class);
    }

    public OrderResponse getOrderById(Long orderId) {
        return commonMapper.convertToEntity(orderService.getOrderById(orderId), OrderResponse.class);
    }

    public List<OrderItemResponse> getOrderItemsByOrderId(Long orderId) {
        return commonMapper.convertToResponseList(orderService.getOrderItemsByOrderId(orderId), OrderItemResponse.class);
    }

    public String deleteOrder(Long orderId) {
        return orderService.deleteOrder(orderId);
    }

}
