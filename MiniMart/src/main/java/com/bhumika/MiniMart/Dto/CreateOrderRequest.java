package com.bhumika.MiniMart.Dto;


import java.util.List;

public class CreateOrderRequest {
    private Long userId;
    private List<OrderItemRequest> products;
    private String address;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<OrderItemRequest> getProducts() { return products; }
    public void setProducts(List<OrderItemRequest> products) { this.products = products; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}

