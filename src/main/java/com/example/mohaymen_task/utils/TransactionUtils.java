package com.example.mohaymen_task.utils;

import com.example.mohaymen_task.configuration.Configuration;
import com.example.mohaymen_task.enums.AccountStatus;
import org.springframework.stereotype.Component;

@Component
public class TransactionUtils {

    private final Configuration configuration;

    public static final long MIN_THRESHOLD = 500_000L;
    public static final long MAX_THRESHOLD = 10_000_000L;

    public TransactionUtils(Configuration configuration) {
        this.configuration = configuration;
    }

    public Integer calculateFee(long value){
        if( value > MIN_THRESHOLD && value < MAX_THRESHOLD){
            return (int) (value * configuration.feePercentage);
        } else if (value < MIN_THRESHOLD){
            return configuration.minimumFee;
        } else if (value > MAX_THRESHOLD){
            return configuration.maximumFee;
        }
        return configuration.minimumFee;
    }

    public boolean isEnabled(AccountStatus accountStatus){
        return !accountStatus.equals(AccountStatus.BLOCKED) && !accountStatus.equals(AccountStatus.DISABLED);
    }

    public boolean accountsNotEqual(String sourceAccountNumber, String destinationAccountNumber){
        return !sourceAccountNumber.equals(destinationAccountNumber);
    }

    public boolean isRemainingSufficient(long remaining, long value){
        return value <= (remaining + calculateFee(value));
    }

}
