package com.bhumika.MiniMart.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double price;

    // CartItem -> Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    // ✅ Many-to-One relation with User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    // CartItem -> Order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // getters and setters
}
