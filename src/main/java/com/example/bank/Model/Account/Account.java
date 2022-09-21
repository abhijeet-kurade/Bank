package com.example.bank.Model.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@AttributeOverrides({
        @AttributeOverride(
                name = "accountNumber",
                column = @Column(name = "account_number")
        )
})
public class Account {
    @Id
    @SequenceGenerator(
            name = "account_number_seq",
            sequenceName = "account_number_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_number_seq"
    )
    Long accountNumber;
    Long pin;
    AccountStatus status;
    Long balance;

    @OneToOne
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customer_id"
    )
    Customer customer;

}
