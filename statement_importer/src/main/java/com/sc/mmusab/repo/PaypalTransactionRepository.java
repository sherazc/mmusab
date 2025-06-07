package com.sc.mmusab.repo;

import com.sc.mmusab.entity.PaypalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaypalTransactionRepository extends JpaRepository<PaypalTransaction, Long> {
  @Query("select ppt from PaypalTransaction ppt")
  List<PaypalTransaction> findAllTransactions();
}