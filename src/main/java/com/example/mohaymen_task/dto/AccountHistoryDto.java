package com.example.mohaymen_task.dto;

import com.example.mohaymen_task.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistoryDto{
    private Long id;
    private Long oldCash;
    private Long newCash;
    private AccountStatus oldAccountStatus;
    private AccountStatus newAccountStatus;
    private UserDto createdBy;
    private LocalDateTime createdDate;
    private AccountDto account;
}
