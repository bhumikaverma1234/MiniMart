package com.bhumika.MiniMart.Service;


import com.bhumika.MiniMart.Dto.OrderItemRequest;
import com.bhumika.MiniMart.Entity.*;
import com.bhumika.MiniMart.Repository.CartItemRepository;
import com.bhumika.MiniMart.Repository.OrderRepository;
import com.bhumika.MiniMart.Repository.ProductRepository;
import com.bhumika.MiniMart.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

     @Autowired
     private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Override
    public Order createOrder(Long userId, List<com.bhumika.MiniMart.Dto.OrderItemRequest> products, String address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);
        order.setAddress(address);

        List<OrderItem> items = new ArrayList<>();
        double totalAmount = 0.0;

        for (OrderItemRequest p : products) {
            Product product = productRepository.findById(p.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(p.getQuantity());
            item.setPrice(product.getPrice());  // ✅ unit price store karna

            totalAmount += product.getPrice() * p.getQuantity(); // ✅ total calculation
            items.add(item);
        }

        order.setItems(items);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }




    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        orderRepository.delete(order);
    }
    @Override
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        order.setStatus(status);
        return orderRepository.save(order);
    }


}

