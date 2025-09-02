package com.bhumika.MiniMart.Service;


import com.bhumika.MiniMart.Dto.OrderItemRequest;
import com.bhumika.MiniMart.Entity.Order;
import com.bhumika.MiniMart.Entity.OrderStatus;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(Long userId, List<OrderItemRequest> products, String address);
    Order updateOrderStatus(Long id, OrderStatus status);
    void deleteOrder(Long id);

}
