package com.sc.mmusab.repo;

import com.sc.mmusab.entity.PaypalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaypalTransactionRepository extends JpaRepository<PaypalTransaction, Long> {
}