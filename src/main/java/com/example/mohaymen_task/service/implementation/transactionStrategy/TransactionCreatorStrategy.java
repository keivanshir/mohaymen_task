package com.example.mohaymen_task.service.implementation.transactionStrategy;

import com.example.mohaymen_task.entity.Transaction;

public interface TransactionCreatorStrategy {
    Transaction createTransaction(String sourceAccountNumber, String destinationAccountNumber);
}
