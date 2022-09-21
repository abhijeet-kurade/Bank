package com.example.bank.Repository;

import com.example.bank.Model.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getAccountByAccountNumber(Long accountNumber);

    @Modifying
    @Transactional
    @Query(
            value = "update account set pin = ?2 where account_number = ?1",
            nativeQuery = true
    )
    int updateAccountPin(Long accountNumber, Long pin);
}
