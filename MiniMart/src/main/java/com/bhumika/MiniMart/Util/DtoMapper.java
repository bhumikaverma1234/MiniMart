package com.bhumika.MiniMart.Util;

import com.bhumika.MiniMart.Dto.*;
import com.bhumika.MiniMart.Entity.*;

public class DtoMapper {

    // ----- User -----
    public static User toUser(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setRole(Role.valueOf(dto.getRole().toUpperCase())); // String -> Enum
        return user;
    }

    public static UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole().name()); // Enum -> String
        return dto;
    }

    // ----- Product -----
    public static Product toProductEntity(ProductDto dto) {
        if (dto == null) return null;
        Product p = new Product();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        return p;
    }

    public static ProductDto toProductDto(Product p) {
        if (p == null) return null;
        ProductDto dto = new ProductDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setPrice(p.getPrice());
        dto.setStock(p.getStock());
        return dto;
    }

    // ----- CartItem -----
    public static CartItem toCartItemEntity(CartItemDto dto, User user, Product product) {
        if (dto == null) return null;
        CartItem c = new CartItem();
        c.setId(dto.getId());
        c.setUser(user);          // FK set
        c.setProduct(product);    // FK set
        c.setQuantity(dto.getQuantity());
        return c;
    }

    public static CartItemDto toCartItemDto(CartItem c) {
        if (c == null) return null;
        CartItemDto dto = new CartItemDto();
        dto.setId(c.getId());
        dto.setUserId(c.getUser() != null ? c.getUser().getId() : null);
        dto.setProductId(c.getProduct() != null ? c.getProduct().getId() : null);
        dto.setQuantity(c.getQuantity());
        dto.setPrice(c.getProduct() != null ? c.getProduct().getPrice() : null);
        return dto;
    }

    // ----- Order -----
    public static Order toOrderEntity(OrderDto dto, User user) {
        if (dto == null) return null;
        Order o = new Order();
        o.setId(dto.getId());
        o.setUser(user);
        o.setOrderDate(dto.getOrderDate());
        o.setTotalAmount(dto.getTotalAmount());
        if (dto.getStatus() != null) {
            o.setStatus(OrderStatus.valueOf(dto.getStatus())); // ✅ String -> Enum
        }
        return o;
    }

    public static OrderDto toOrderDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setAddress(order.getAddress());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus().name());

        // ✅ Map items
        if (order.getItems() != null) {
            dto.setItems(
                    order.getItems().stream().map(item -> {
                        OrderItemDto itemDto = new OrderItemDto();
                        itemDto.setProductId(item.getProduct().getId());
                        itemDto.setProductName(item.getProduct().getName());
                        itemDto.setQuantity(item.getQuantity());
                        itemDto.setPrice(item.getPrice());
                        itemDto.setTotalPrice(item.getPrice() * item.getQuantity());
                        return itemDto;
                    }).toList()
            );
        }

        return dto;
    }
    public static OrderItemDto toOrderItemDto(OrderItem item) {
        OrderItemDto itemDto = new OrderItemDto();
        itemDto.setProductId(item.getProduct().getId());
        itemDto.setProductName(item.getProduct().getName());
        itemDto.setQuantity(item.getQuantity());

        // ✅ Single product ka price
        itemDto.setPrice(item.getPrice());

        // ✅ Total price = price × quantity
        itemDto.setTotalPrice(item.getPrice() * item.getQuantity());

        return itemDto;
    }


    // ----- Payment -----
    public static Payment toPaymentEntity(PaymentDto dto, Order order) {
        if (dto == null) return null;
        Payment p = new Payment();
        p.setId(dto.getId());
        p.setOrder(order);
        p.setAmount(dto.getAmount());
        p.setPaymentMethod(dto.getPaymentMethod());
        p.setStatus(dto.getStatus());
        return p;
    }

    public static PaymentDto toPaymentDto(Payment p) {
        if (p == null) return null;
        PaymentDto dto = new PaymentDto();
        dto.setId(p.getId());
        dto.setOrderId(p.getOrder() != null ? p.getOrder().getId() : null);
        dto.setAmount(p.getAmount());
        dto.setPaymentMethod(p.getPaymentMethod());
        dto.setStatus(p.getStatus());
        return dto;
    }
}
