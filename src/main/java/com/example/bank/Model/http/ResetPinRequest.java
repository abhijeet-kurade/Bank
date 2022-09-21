package com.example.bank.Model.http;

import lombok.Data;

@Data
public class ResetPinRequest {
    Long accountNumber;
    Long oldPin;
    Long newPin;
}
