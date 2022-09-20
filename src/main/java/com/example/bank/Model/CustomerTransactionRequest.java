package com.example.bank.Model;

import lombok.Data;

@Data
public class CustomerTransactionRequest {
    int accountNumber;
    String pin;
    int amount;
    String message;
}
