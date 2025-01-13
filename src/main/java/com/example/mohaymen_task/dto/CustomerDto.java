package com.example.mohaymen_task.dto;

import com.example.mohaymen_task.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String identificationNumber;
    private LocalDate birthOrEstablishmentDate;
    private CustomerType customerType;
    private String phoneNumber;
    private String address;
    private String postalCode;
    private Set<AccountDto> customerAccount = new HashSet<>();
}
