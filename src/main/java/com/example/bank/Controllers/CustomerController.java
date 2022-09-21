package com.example.bank.Controllers;

import com.example.bank.Model.http.TransactionRequest;
import com.example.bank.Model.http.TransactionResponse;
import com.example.bank.Services.BankService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {

    private final BankService bankService;

    @GetMapping
    public String hello(){
        return "Hello World!";
    }


    @PostMapping(path = "withdraw")
    public TransactionResponse withdrawMoney(@RequestBody TransactionRequest request){

        return bankService.withdrawMoney(request);
    }

    @PostMapping(path = "deposit")
    public TransactionResponse depositMoney(@RequestBody TransactionRequest request){

        return bankService.depositMoney(request);
    }
}
