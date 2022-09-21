package com.example.bank.Model.http;

import com.example.bank.Model.Account.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountCreateResponse {
    int reposeCode;
    String message;
    Account account;
}
