package com.example.mohaymen_task.service.implementation.accountStrategy;

import com.example.mohaymen_task.enums.TransactionType;
import com.example.mohaymen_task.repository.AccountHistoryRepository;
import com.example.mohaymen_task.repository.AccountRepository;
import com.example.mohaymen_task.utils.TransactionUtils;
import org.springframework.stereotype.Component;

@Component
public class OperatorSelector {

    private final AccountRepository accountRepository;
    private final AccountHistoryRepository accountHistoryRepository;
    private final TransactionUtils transactionUtils;

    public OperatorSelector(AccountRepository accountRepository,
                            TransactionUtils transactionUtils,
                            AccountHistoryRepository accountHistoryRepository) {
        this.accountRepository = accountRepository;
        this.accountHistoryRepository = accountHistoryRepository;
        this.transactionUtils = transactionUtils;
    }

    public OperationStrategy getStrategy(TransactionType type) {

        return switch (type) {
            case DEPOSIT -> new Increase(accountRepository, accountHistoryRepository, transactionUtils);
            case WITHDRAW -> new Decrease(accountRepository, accountHistoryRepository, transactionUtils);
            case TRANSFER -> new Transmit(accountRepository, accountHistoryRepository, transactionUtils);
        };
    }
}
