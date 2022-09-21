package com.example.bank.Model.http;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {
    Long responseCode;
    String message;
    Long currentAmount;
}
