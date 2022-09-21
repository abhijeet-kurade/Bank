package com.example.bank.Model.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewAccountCreateRequest {
    String name;
    String address;
    String email;
    String phone;
}
