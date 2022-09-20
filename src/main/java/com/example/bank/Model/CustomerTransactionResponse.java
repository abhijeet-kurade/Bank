package com.example.bank.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerTransactionResponse {
    int responseCode;
    String message;
    int currentAmount;
}
