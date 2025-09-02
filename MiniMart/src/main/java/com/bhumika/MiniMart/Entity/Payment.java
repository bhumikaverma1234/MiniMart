package com.bhumika.MiniMart.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentMethod;
    private String status;
    private double amount;

    // Payment -> Order
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // getters and setters
}
