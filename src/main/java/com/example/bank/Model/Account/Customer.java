package com.example.bank.Model.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(
                name = "email_id_unique",
                columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "phone_unique",
                        columnNames = "phone"
                )
        }
)

public class Customer{
    @Id
    @SequenceGenerator(
            name = "customer_id_seq",
            sequenceName = "customer_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_seq"
    )
    @Column(name = "customer_id")
    Long customerId;
    String name;
    String address;
    String email;
    String phone;

}
