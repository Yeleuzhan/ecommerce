package kz.ecommerce.controller;

import kz.ecommerce.dto.order.OrderResponse;
import kz.ecommerce.dto.perfume.FullPerfumeResponse;
import kz.ecommerce.dto.perfume.PerfumeRequest;
import kz.ecommerce.dto.user.BaseUserResponse;
import kz.ecommerce.dto.user.UserResponse;
import kz.ecommerce.mapper.OrderMapper;
import kz.ecommerce.mapper.PerfumeMapper;
import kz.ecommerce.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserMapper userMapper;
    private final PerfumeMapper perfumeMapper;
    private final OrderMapper orderMapper;

    public AdminController(UserMapper userMapper, PerfumeMapper perfumeMapper, OrderMapper orderMapper) {
        this.userMapper = userMapper;
        this.perfumeMapper = perfumeMapper;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<BaseUserResponse>> getAllUsers() {
        return ResponseEntity.ok(userMapper.getAllUsers());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userMapper.getUserById(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<FullPerfumeResponse> addPerfume(@RequestPart(name = "file", required = false)MultipartFile file,
                                                          @RequestPart("perfume") @Valid PerfumeRequest perfume,
                                                          BindingResult bindingResult) {
        return ResponseEntity.ok(perfumeMapper.savePerfume(perfume, file, bindingResult));
    }

    @PostMapping("/edit")
    public ResponseEntity<FullPerfumeResponse> updatePerfume(@RequestPart(name = "file", required = false)MultipartFile file,
                                                          @RequestPart("perfume") @Valid PerfumeRequest perfume,
                                                          BindingResult bindingResult) {
        return ResponseEntity.ok(perfumeMapper.savePerfume(perfume, file, bindingResult));
    }

    @DeleteMapping("/delete/{perfumeId}")
    public ResponseEntity<String> deletePerfume(@PathVariable Long perfumeId) {
        return ResponseEntity.ok(perfumeMapper.deletePerfume(perfumeId));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderMapper.getAllOrders());
    }

    @GetMapping("/order/{userEmail}")
    public ResponseEntity<List<OrderResponse>> getUserOrdersByEmail(@PathVariable String userEmail) {
        return ResponseEntity.ok(orderMapper.getUserOrders(userEmail));
    }

    @DeleteMapping("/order/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.deleteOrder(orderId));
    }

}
