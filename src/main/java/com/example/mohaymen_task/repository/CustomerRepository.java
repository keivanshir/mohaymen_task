package com.example.mohaymen_task.repository;

import com.example.mohaymen_task.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByIdentificationNumber(String idNumber);
}
