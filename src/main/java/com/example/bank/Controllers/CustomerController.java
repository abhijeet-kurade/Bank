package com.example.bank.Controllers;

import com.example.bank.Model.CustomerTransactionRequest;
import com.example.bank.Model.CustomerTransactionResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {

    @GetMapping
    public String hello(){
        return "Hello World!";
    }


    @PostMapping(path = "withdraw")
    public CustomerTransactionResponse withdrawMoney(@RequestBody CustomerTransactionRequest request){

        System.out.println(request);

        return CustomerTransactionResponse.builder()
                .responseCode(request.getAmount())
                .message("Transaction Succeeded")
                .currentAmount(5000)
                .build();
    }
}
