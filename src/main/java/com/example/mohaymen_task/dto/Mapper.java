package com.example.mohaymen_task.dto;

import com.example.mohaymen_task.entity.*;

import java.util.stream.Collectors;

public class Mapper {

    public AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setRemaining(account.getRemaining());
        accountDto.setAccountStatus(account.getAccountStatus());
        accountDto.setCreatedDate(account.getCreatedDate());
        accountDto.setModifiedDate(account.getModifiedDate());

        return accountDto;
    }

    public Account mapToAccount(AccountDto accountDto){
        Account account = new Account();

        account.setId(accountDto.getId());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setRemaining(accountDto.getRemaining());
        account.setAccountStatus(accountDto.getAccountStatus());
        account.setCreatedDate(accountDto.getCreatedDate());
        account.setModifiedDate(accountDto.getModifiedDate());

        return account;
    }

    public AccountHistoryDto mapToAccountHistoryDto(AccountHistory accountHistory){
        AccountHistoryDto accountHistoryDto = new AccountHistoryDto();

        accountHistoryDto.setId(accountHistory.getId());
        accountHistoryDto.setOldAccountStatus(accountHistory.getOldAccountStatus());
        accountHistoryDto.setOldCash(accountHistory.getOldCash());
        accountHistoryDto.setNewAccountStatus(accountHistory.getNewAccountStatus());
        accountHistoryDto.setNewCash(accountHistory.getNewCash());
        if (accountHistory.getCreatedBy() != null){
            accountHistoryDto.setCreatedBy(mapToUserDto(accountHistory.getCreatedBy()));
        }
        accountHistoryDto.setCreatedDate(accountHistory.getCreatedDate());

        return accountHistoryDto;
    }

    public AccountHistory mapToAccountHistory(AccountHistoryDto accountHistoryDto){
        AccountHistory accountHistory = new AccountHistory();

        accountHistory.setId(accountHistoryDto.getId());
        accountHistory.setOldAccountStatus(accountHistoryDto.getOldAccountStatus());
        accountHistory.setOldCash(accountHistoryDto.getOldCash());
        accountHistory.setNewAccountStatus(accountHistoryDto.getNewAccountStatus());
        accountHistory.setNewCash(accountHistoryDto.getNewCash());
        if (accountHistoryDto.getCreatedBy() != null){
            accountHistory.setCreatedBy(mapToUser(accountHistoryDto.getCreatedBy()));
        }
        accountHistory.setCreatedDate(accountHistory.getCreatedDate());

        return accountHistory;
    }


    public UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());

        return userDto;
    }


    public User mapToUser(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }

    public CustomerDto mapToCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setIdentificationNumber(customer.getIdentificationNumber());
        customerDto.setCustomerType(customer.getCustomerType());
        customerDto.setAddress(customer.getAddress());
        customerDto.setPostalCode(customer.getPostalCode());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        customerDto.setBirthOrEstablishmentDate(customer.getBirthOrEstablishmentDate());
        if (customer.getCustomerAccount() != null){
            customerDto.setCustomerAccount(customer.getCustomerAccount()
                    .stream()
                    .map(this::mapToAccountDto)
                    .collect(Collectors.toSet()));
        }

        return customerDto;
    }

    public Customer mapToCustomer(CustomerDto customerDto){
        Customer customer = new Customer();

        customer.setId(customerDto.getId());
        customer.setIdentificationNumber(customerDto.getIdentificationNumber());
        customer.setCustomerType(customerDto.getCustomerType());
        customer.setAddress(customerDto.getAddress());
        customer.setPostalCode(customerDto.getPostalCode());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setBirthOrEstablishmentDate(customerDto.getBirthOrEstablishmentDate());
        if (customerDto.getCustomerAccount() != null){
            customer.setCustomerAccount(customerDto.getCustomerAccount()
                    .stream()
                    .map(this::mapToAccount)
                    .collect(Collectors.toSet()));
        }

        return customer;
    }

    public TransactionDto mapToTransactionDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setTransactionStatus(transaction.getTransactionStatus());
        transactionDto.setTransactionType(transaction.getTransactionType());
        if (transaction.getSourceAccount() != null){
            transactionDto.setSourceAccount(mapToAccountDto(transaction.getSourceAccount()));
        }

        if (transaction.getDestinationAccount() != null){
            transactionDto.setDestinationAccount(mapToAccountDto(transaction.getDestinationAccount()));
        }

        return transactionDto;
    }

    public Transaction mapToTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setTransactionStatus(transactionDto.getTransactionStatus());
        transaction.setTransactionType(transactionDto.getTransactionType());
        if (transactionDto.getSourceAccount() != null){
            transaction.setSourceAccount(mapToAccount(transactionDto.getSourceAccount()));
        }

        if (transactionDto.getDestinationAccount() != null){
            transaction.setDestinationAccount(mapToAccount(transactionDto.getDestinationAccount()));
        }
        return transaction;
    }

}
