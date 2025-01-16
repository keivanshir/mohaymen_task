package com.example.mohaymen_task.service.implementation.accountStrategy;

public interface OperationStrategy {

    void operate(String sourceAccountNumber, String destinationAccountNumber, Long value);
}
