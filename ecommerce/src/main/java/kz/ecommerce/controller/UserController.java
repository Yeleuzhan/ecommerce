package kz.ecommerce.controller;

import kz.ecommerce.dto.auth.AuthenticationRequest;
import kz.ecommerce.dto.order.OrderItemResponse;
import kz.ecommerce.dto.order.OrderRequest;
import kz.ecommerce.dto.order.OrderResponse;
import kz.ecommerce.dto.perfume.FullPerfumeResponse;
import kz.ecommerce.dto.review.ReviewRequest;
import kz.ecommerce.dto.review.ReviewResponse;
import kz.ecommerce.dto.user.UpdatedUserRequest;
import kz.ecommerce.dto.user.UserResponse;
import kz.ecommerce.mapper.OrderMapper;
import kz.ecommerce.mapper.UserMapper;
import kz.ecommerce.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public UserController(UserMapper userMapper, OrderMapper orderMapper, SimpMessagingTemplate simpMessagingTemplate) {
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(userMapper.getUserInfo(user.getEmail()));
    }

    @PutMapping("/edit")
    public ResponseEntity<UserResponse> updateUserInfo(@AuthenticationPrincipal UserPrincipal user,
                                                       @Valid @RequestBody UpdatedUserRequest request,
                                                       BindingResult bindingResult) {
        return ResponseEntity.ok(userMapper.updateUserInfo(user.getEmail(), request, bindingResult));
    }

    @PostMapping("/cart")
    public ResponseEntity<List<FullPerfumeResponse>> getCart(@RequestBody List<Long> perfumeIds) {
        return ResponseEntity.ok(userMapper.getCart(perfumeIds));
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> postOrder(@Valid @RequestBody OrderRequest orderRequest, BindingResult bindingResult) {
        return ResponseEntity.ok(orderMapper.postOrder(orderRequest, bindingResult));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(orderMapper.getUserOrders(userPrincipal.getEmail()));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.getOrderById(orderId));
    }

    @GetMapping("/order/{orderId}/items")
    public ResponseEntity<List<OrderItemResponse>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.getOrderItemsByOrderId(orderId));
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewResponse> addReviewToPerfume(@Valid @RequestBody ReviewRequest reviewRequest,
                                                             BindingResult bindingResult) {
        ReviewResponse reviewResponse = userMapper.addReviewToPerfume(reviewRequest, reviewRequest.getPerfumeId(), bindingResult);
        simpMessagingTemplate.convertAndSend("/topic/reviews/" + reviewRequest.getPerfumeId(), reviewResponse);

        return ResponseEntity.ok(reviewResponse);
    }

}
