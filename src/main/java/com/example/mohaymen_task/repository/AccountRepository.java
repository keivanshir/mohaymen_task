package com.example.mohaymen_task.repository;

import com.example.mohaymen_task.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByAccountNumber(String accountNumber);

    Optional<Account> findAccountByCustomer_Id(Long customerId);
}
