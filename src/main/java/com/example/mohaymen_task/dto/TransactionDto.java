package com.example.mohaymen_task.dto;

import com.example.mohaymen_task.enums.TransactionStatus;
import com.example.mohaymen_task.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto{
    private Long id;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private String trackingCode;
    private Long value;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
}
