package com.bhumika.MiniMart.Service;

import com.bhumika.MiniMart.Entity.Order;
import com.bhumika.MiniMart.Entity.Payment;
import com.bhumika.MiniMart.Repository.OrderRepository;
import com.bhumika.MiniMart.Repository.PaymentRepository;
import com.bhumika.MiniMart.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bhumika.MiniMart.Entity.OrderStatus;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }


    @Override
    public Payment makePayment(Long orderId, Double amount, String method) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + orderId));

        if (order.getTotalAmount() != amount) {
            throw new RuntimeException("Payment amount does not match order total!");
        }

        // Create Payment
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentMethod(method);
        payment.setStatus("SUCCESS");
        payment.setOrder(order);

        // Link payment with order
        order.setPayment(payment);

        // Update order status after payment
        order.setStatus(OrderStatus.PAID);

        // Save both order and payment
        orderRepository.save(order);
        return paymentRepository.save(payment);
    }




}

