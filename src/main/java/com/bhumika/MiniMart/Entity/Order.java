package com.bhumika.MiniMart.Entity;
import com.bhumika.MiniMart.Entity.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "orders")  // reserved keyword fix
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;
    private double totalAmount;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference   // ❌ Infinite recursion se bachata hai
    private User user;

    // Order -> CartItems
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(nullable = false)
    private String address;

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // Order -> Payment
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> items = new ArrayList<>();

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }


    @Enumerated(EnumType.STRING)   // enum ko string form me store karega
    private OrderStatus status = OrderStatus.PENDING; // default status

    // getters and setters
}
