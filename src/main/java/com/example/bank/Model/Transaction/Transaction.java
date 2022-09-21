package com.example.bank.Model.Transaction;

import com.example.bank.Model.Account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_id_seq",
            sequenceName = "transaction_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_id_seq"
    )
    @Column(name = "transaction_id")
    int transactionId;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "account_number",
            referencedColumnName = "account_number"
    )
    Account account;
    LocalDateTime timestamp;
    TransactionType type;
    TransactionStatus status;
    Long amount;
    String message;
}
