package com.example.mohaymen_task.service;

import com.example.mohaymen_task.dto.Response;
import com.example.mohaymen_task.dto.TransactionDto;
import com.example.mohaymen_task.enums.TransactionStatus;
import com.example.mohaymen_task.enums.TransactionType;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    Response<TransactionDto> createTransaction(TransactionDto transactionDto);
    Response<TransactionStatus> getTransactionStatusByTrackingCode(Long trackingCode);

    Response<List<TransactionDto>> findAllTransactionsByFilter(int page,
                                                     int size,
                                                     TransactionType type,
                                                     String accountNumber,
                                                     Long fromValue,
                                                     Long toValue,
                                                     LocalDate fromDate,
                                                     LocalDate toDate);

}
