package com.bhumika.MiniMart.Controller;

import com.bhumika.MiniMart.Dto.PaymentDto;
import com.bhumika.MiniMart.Entity.Payment;
import com.bhumika.MiniMart.Service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(
        name = "Payments",
        description = "APIs to handle payment creation and retrieve payment details"
)
@RestController
@RequestMapping("/api/payments")
public class PaymentController{

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(
            summary = "Make a payment",
            description = "Creates a new payment for a given order with amount and payment method"
    )
    @PostMapping
    public ResponseEntity<Payment> makePayment(@Valid @RequestBody PaymentDto request) {
        Payment payment = paymentService.makePayment(
                request.getOrderId(),
                request.getAmount(),
                request.getPaymentMethod()
        );
        return ResponseEntity.ok(payment);
    }
    @Operation(
            summary = "Get payment by ID",
            description = "Fetches details of a specific payment by its ID"
    )
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @Operation(
            summary = "Get all payments",
            description = "Returns a list of all payments made"
    )
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}

