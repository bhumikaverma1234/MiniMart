package com.bhumika.MiniMart.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long id;

    @NotNull(message = "Order id is required")
    private Long orderId;

    @NotNull(message = "Amount is required")
    @Min(value = 0, message = "Amount must be >= 0")
    private Double amount;

    @NotNull(message = "Method is required")
    private String paymentMethod; // UPI/CARD/WALLET/COD

    private String status; // SUCCESS / FAILED / PENDING
}

