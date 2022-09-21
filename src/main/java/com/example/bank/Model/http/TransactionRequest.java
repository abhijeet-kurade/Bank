package com.example.bank.Model.http;

import lombok.Data;

@Data
public class TransactionRequest {
    Long accountNumber;
    Long pin;
    Long amount;
    String message;
}
