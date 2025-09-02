package com.bhumika.MiniMart.Controller;

import com.bhumika.MiniMart.Dto.CreateOrderRequest;
import com.bhumika.MiniMart.Entity.Order;
import com.bhumika.MiniMart.Entity.OrderStatus;
import com.bhumika.MiniMart.Service.OrderService;
import com.bhumika.MiniMart.Util.DtoMapper;
import com.bhumika.MiniMart.Dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public OrderDto createOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(
                request.getUserId(),
                request.getProducts(),
                request.getAddress()
        );
        return DtoMapper.toOrderDto(order);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return DtoMapper.toOrderDto(order);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(DtoMapper::toOrderDto)
                .toList();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully with id: " + id);
    }
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> requestBody) {

        String statusStr = requestBody.get("status");

        if (statusStr == null || statusStr.isBlank()) {
            return ResponseEntity.badRequest().body("Status is required in request body");
        }

        OrderStatus status;
        try {
            status = OrderStatus.valueOf(statusStr.trim().toUpperCase()); // trim spaces & case-insensitive
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Invalid status value. Allowed: " + Arrays.toString(OrderStatus.values()));
        }

        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }




}
