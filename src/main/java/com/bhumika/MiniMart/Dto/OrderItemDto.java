package com.bhumika.MiniMart.Dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
    private double totalPrice;
}
