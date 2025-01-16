package com.example.mohaymen_task.service.implementation.transactionStrategy;

import com.example.mohaymen_task.enums.TransactionType;

public class TransactionStrategy {

    public static TransactionCreatorStrategy getTransaction(TransactionType type){
        return switch (type){
            case DEPOSIT -> new Deposit();
            case TRANSFER ->  new Transfer();
            case WITHDRAW ->  new Withdraw();
        };
    }
}
