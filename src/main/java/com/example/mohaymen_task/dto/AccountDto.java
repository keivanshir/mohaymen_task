package com.example.mohaymen_task.dto;

import com.example.mohaymen_task.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private Long accountNumber;
    private Long remaining;
    private AccountStatus accountStatus;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
