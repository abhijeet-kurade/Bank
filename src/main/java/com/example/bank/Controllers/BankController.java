package com.example.bank.Controllers;

import com.example.bank.Model.Account.Account;
import com.example.bank.Model.Account.Customer;
import com.example.bank.Model.http.AccountCreateResponse;
import com.example.bank.Model.http.NewAccountCreateRequest;
import com.example.bank.Model.http.NewAccountCreateResponse;
import com.example.bank.Services.BankService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/bank")
@AllArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping(path = "create-account")
    public NewAccountCreateResponse createAccount(@RequestBody NewAccountCreateRequest customer){
        AccountCreateResponse account = bankService.createAccount(customer);
        return NewAccountCreateResponse.builder()
                .responseCode(account.getReposeCode())
                .message(account.getMessage())
                .accountNumber(account.getAccount()==null ? null : account.getAccount().getAccountNumber())
                .pin(account.getAccount()==null ? null : account.getAccount().getPin())
                .build();
    }

}
