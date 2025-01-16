package com.example.mohaymen_task.service.implementation.transactionStrategy;

import com.example.mohaymen_task.entity.Transaction;
import com.example.mohaymen_task.enums.TransactionType;

public class Withdraw implements TransactionCreatorStrategy{
    @Override
    public Transaction createTransaction(String sourceAccountNumber, String destinationAccountNumber) {
        Transaction transaction = new Transaction();

        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setSourceAccountNumber(sourceAccountNumber);

        return transaction;
    }
}
