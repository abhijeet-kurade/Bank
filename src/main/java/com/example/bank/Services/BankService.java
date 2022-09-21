package com.example.bank.Services;

import com.example.bank.Model.Account.Account;
import com.example.bank.Model.Account.AccountStatus;
import com.example.bank.Model.Account.Customer;
import com.example.bank.Model.http.AccountCreateResponse;
import com.example.bank.Model.http.NewAccountCreateRequest;
import com.example.bank.Repository.AccountRepository;
import com.example.bank.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public AccountCreateResponse createAccount(NewAccountCreateRequest customer){

        Customer newCustomer = Customer.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();

        try {
            Customer customer1 = customerRepository.save(newCustomer);
        }
        catch (Exception e){
            return AccountCreateResponse.builder()
                    .reposeCode(500)
                    .message("Failed : Email or Phone is already registered.")
                    .build();
        }

        Account newAccount = Account.builder()
                .balance(0l)
                .pin(12234l)
                .status(AccountStatus.ACTIVE)
                .customer(newCustomer)
                .build();
        accountRepository.save(newAccount);

        return AccountCreateResponse.builder()
                .reposeCode(200)
                .message("Succeeded : Bank account created successfully")
                .account(newAccount)
                .build();

    }
}
