package com.bhumika.MiniMart.Service;

import com.bhumika.MiniMart.Entity.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
//    Payment createPayment(Payment payment);
    Payment makePayment(Long orderId, Double amount, String method);

}

