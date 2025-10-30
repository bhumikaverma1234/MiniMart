package com.bhumika.MiniMart.Controller;

import com.bhumika.MiniMart.Dto.CreateOrderRequest;
import com.bhumika.MiniMart.Entity.Order;
import com.bhumika.MiniMart.Entity.OrderStatus;
import com.bhumika.MiniMart.Service.OrderService;
import com.bhumika.MiniMart.Util.DtoMapper;
import com.bhumika.MiniMart.Dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

import java.util.List;

@Tag(name = "Orders", description = "APIs for placing and managing orders")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @Operation(summary = "Create order", description = "Place a new order for products")
    @PostMapping("/create")
    public OrderDto createOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(
                request.getUserId(),
                request.getProducts(),
                request.getAddress()
        );
        return DtoMapper.toOrderDto(order);
    }

    @Operation(summary = "Get order by ID", description = "Fetch details of a specific order using its ID")
    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return DtoMapper.toOrderDto(order);
    }
    @Operation(summary = "Get all orders", description = "Fetch all orders placed by customers")
    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(DtoMapper::toOrderDto)
                .toList();
    }
    @Operation(summary = "Delete Order by ID", description = "Delete all orders placed by customers")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully with id: " + id);
    }
    @Operation(summary = "Update Order By Id", description = "Update all orders placed by customers")
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
