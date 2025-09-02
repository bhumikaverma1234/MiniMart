package com.bhumika.MiniMart.Controller;

import com.bhumika.MiniMart.Entity.Payment;
import com.bhumika.MiniMart.Service.PaymentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController{

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment makePayment(@RequestParam Long orderId,
                               @RequestParam Double amount,
                               @RequestParam String method) {
        return paymentService.makePayment(orderId, amount, method);
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}

