package com.example.mohaymen_task.service.implementation.transactionStrategy;

import com.example.mohaymen_task.entity.Transaction;
import com.example.mohaymen_task.enums.TransactionType;

public class Deposit implements TransactionCreatorStrategy {
    @Override
    public Transaction createTransaction(String sourceAccountNumber, String destinationAccountNumber) {
        Transaction transaction = new Transaction();

        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setDestinationAccountNumber(destinationAccountNumber);

        return transaction;
    }
}
