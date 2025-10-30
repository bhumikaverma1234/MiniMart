package com.bhumika.MiniMart.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;

    @NotNull(message = "User id is required")
    private Long userId;

    private LocalDate orderDate;
    private String address;
    private Double totalAmount;

    // If you want order items in response:
    private List<OrderItemDto> items;
    private String status; // PENDING, PAID, SHIPPED, DELIVERED
}
