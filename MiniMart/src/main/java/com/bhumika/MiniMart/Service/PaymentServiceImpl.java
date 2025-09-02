package com.bhumika.MiniMart.Service;

import com.bhumika.MiniMart.Entity.Order;
import com.bhumika.MiniMart.Entity.Payment;
import com.bhumika.MiniMart.Repository.OrderRepository;
import com.bhumika.MiniMart.Repository.PaymentRepository;
import com.bhumika.MiniMart.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    // Constructor Injection (best practice)
//    public PaymentServiceImpl(PaymentRepository paymentRepository,
//                              OrderRepository orderRepository) {
//        this.paymentRepository = paymentRepository;
//        this.orderRepository = orderRepository;
//    }

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
        // order fetch karo
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        Payment payment = new Payment();
        payment.setOrder(order); // ✅ relation set
        payment.setAmount(amount);
        payment.setPaymentMethod(method);
        payment.setStatus("SUCCESS"); // ya "PENDING"

        return paymentRepository.save(payment);
    }



}

