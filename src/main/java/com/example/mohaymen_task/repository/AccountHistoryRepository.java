package com.example.mohaymen_task.repository;

import com.example.mohaymen_task.entity.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {
}
