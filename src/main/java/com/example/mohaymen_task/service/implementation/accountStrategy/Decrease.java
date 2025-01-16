package com.example.mohaymen_task.service.implementation.accountStrategy;

import com.example.mohaymen_task.entity.Account;
import com.example.mohaymen_task.entity.AccountHistory;
import com.example.mohaymen_task.exception.AccountDisabledException;
import com.example.mohaymen_task.exception.InsufficientRemainingException;
import com.example.mohaymen_task.exception.NotFoundException;
import com.example.mohaymen_task.repository.AccountHistoryRepository;
import com.example.mohaymen_task.repository.AccountRepository;
import com.example.mohaymen_task.utils.TransactionUtils;
import org.springframework.stereotype.Component;

@Component
public class Decrease implements OperationStrategy {

    private final AccountRepository accountRepository;
    private final AccountHistoryRepository accountHistoryRepository;
    private final TransactionUtils transactionUtils;

    public Decrease(AccountRepository accountRepository, AccountHistoryRepository accountHistoryRepository, TransactionUtils transactionUtils) {
        this.accountRepository = accountRepository;
        this.accountHistoryRepository = accountHistoryRepository;
        this.transactionUtils = transactionUtils;
    }

    @Override
    public void operate(String sourceAccountNumber, String destinationAccountNumber, Long value) {
        Account sourceAccount = accountRepository.findAccountByAccountNumber(sourceAccountNumber)
                .orElseThrow(() -> new NotFoundException("شماره حساب موجود نیست!"));

        if (transactionUtils.isEnabled(sourceAccount.getAccountStatus())){
            if (transactionUtils.isRemainingSufficient(sourceAccount.getRemaining(), value)){
                Long oldRemaining = sourceAccount.getRemaining();
                sourceAccount.setRemaining(sourceAccount.getRemaining() - value);
                Transmit.saveToAccount(accountRepository, accountHistoryRepository, sourceAccount, oldRemaining);
            } else {
                throw new InsufficientRemainingException("موجودی کمتر از مقدار انتقال است!");
            }
        } else {
            throw new AccountDisabledException("شماره حساب شما مسدود است!");
        }
    }
}
