package com.example.bank.Model.http;

import com.example.bank.Model.Account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewAccountCreateResponse {
    Integer responseCode;
    String message;
    Long accountNumber;
    Long pin;
}
