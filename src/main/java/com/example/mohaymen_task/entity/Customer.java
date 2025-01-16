package com.example.mohaymen_task.entity;

import com.example.mohaymen_task.enums.CustomerType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "IDENTITFICATION_NUMBER")
    private String identificationNumber;

    @Column(name = "BIRTH_OR_ESTABLISHMENT_DATE")
    private LocalDate birthOrEstablishmentDate;

    @Column(name = "CUSTOMER_TYPE")
    private CustomerType customerType;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    private String address;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

}
