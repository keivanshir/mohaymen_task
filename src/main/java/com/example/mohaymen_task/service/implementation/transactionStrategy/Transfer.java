package com.example.mohaymen_task.service.implementation.transactionStrategy;

import com.example.mohaymen_task.entity.Transaction;
import com.example.mohaymen_task.enums.TransactionType;

public class Transfer implements TransactionCreatorStrategy {
    @Override
    public Transaction createTransaction(String sourceAccountNumber, String destinationAccountNumber) {
        Transaction transaction = new Transaction();

        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setSourceAccountNumber(sourceAccountNumber);
        transaction.setDestinationAccountNumber(destinationAccountNumber);

        return transaction;
    }
}
