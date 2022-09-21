package com.example.bank.Services;

import com.example.bank.Model.Account.Account;
import com.example.bank.Model.Account.AccountStatus;
import com.example.bank.Model.Account.Customer;
import com.example.bank.Model.Transaction.Transaction;
import com.example.bank.Model.Transaction.TransactionStatus;
import com.example.bank.Model.Transaction.TransactionType;
import com.example.bank.Model.http.*;
import com.example.bank.Repository.AccountRepository;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BankService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

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

    public String resetPin(ResetPinRequest data){

        Long accountNumber = data.getAccountNumber();
        Long oldPin = data.getOldPin();
        Long newPin = data.getNewPin();

        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        if(account == null){
            return "Failed: The account does not exist.";
        }

        if(account.getPin() != Long.parseLong(String.valueOf(oldPin))){
            return "Failed: Incorrect PIN.";
        }

        try{
            accountRepository.updateAccountPin(accountNumber, newPin);
        }
        catch (Exception e){
            System.out.println(e);
            return "Failed: Server Error.";
        }

        return "Pin reset successfully.";
    }

    public TransactionResponse withdrawMoney(TransactionRequest data){
        Utility.sleep(10);
        Long accountNumber = data.getAccountNumber();
        Long amount = Long.parseLong(String.valueOf(data.getAmount()));
        Long pin = data.getPin();

        Account account = accountRepository.getAccountByAccountNumber(accountNumber);

        if(account == null){
            return TransactionResponse.builder()
                    .responseCode(500l)
                    .message("Account does not exist.")
                    .build();
        }

        if(!account.getPin().equals(pin)){
            return TransactionResponse.builder()
                    .responseCode(500l)
                    .message("incorrect PIN.")
                    .build();
        }

        if(account.getBalance() < amount){
            return TransactionResponse.builder()
                    .responseCode(500l)
                    .message("Can not withdraw amount greater than balance balance is: "+ account.getBalance())
                    .build();
        }

        Transaction transaction = Transaction.builder()
                .account(account)
                .type(TransactionType.DEBIT)
                .timestamp(LocalDateTime.now())
                .amount(amount)
                .message(data.getMessage())
                .status(TransactionStatus.SUCCEEDED)
                .build();

        transactionRepository.save(transaction);

        Long balance = account.getBalance() - amount;

        accountRepository.updateAccountBalance(accountNumber, balance);

        return TransactionResponse.builder()
                .responseCode(200l)
                .message("Account debited successfully.")
                .currentAmount(balance)
                .build();
    }
    public TransactionResponse depositMoney(TransactionRequest data){
        Utility.sleep(10);
        Long accountNumber = data.getAccountNumber();
        Long amount = data.getAmount();

        Account account = accountRepository.getAccountByAccountNumber(accountNumber);

        if(account == null){
            return TransactionResponse.builder()
                    .responseCode(500l)
                    .message("Account does not exist.")
                    .build();
        }

        Transaction transaction = Transaction.builder()
                .account(account)
                .type(TransactionType.CREDIT)
                .timestamp(LocalDateTime.now())
                .amount(amount)
                .message(data.getMessage())
                .status(TransactionStatus.SUCCEEDED)
                .build();

        transactionRepository.save(transaction);

        Long balance = account.getBalance() + amount;

        accountRepository.updateAccountBalance(accountNumber, balance);

        return TransactionResponse.builder()
                .responseCode(200l)
                .message("Account credited successfully.")
                .currentAmount(balance)
                .build();
    }
}
