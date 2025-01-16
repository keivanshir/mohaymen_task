package com.example.mohaymen_task.service;

import com.example.mohaymen_task.dto.AccountDto;
import com.example.mohaymen_task.dto.CustomerDto;
import com.example.mohaymen_task.dto.Response;

public interface AccountService {
    Response<AccountDto> createAccount(CustomerDto customerDto);
    Response<AccountDto> updateAccount(AccountDto accountDto);
    Response<String> getAccountNumberByIdentificationNumber(String identificationNumber);
    Response<AccountDto> getCustomerByAccountNumber(String accountNumber);
    Response<String> getRemainingCashByAccountNumber(String accountNumber);
    Response<String> deleteAccount(String accountNumber);
}
