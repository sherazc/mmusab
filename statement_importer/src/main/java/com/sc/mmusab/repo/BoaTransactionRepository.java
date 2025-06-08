package com.sc.mmusab.repo;

import com.sc.mmusab.entity.BoaTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoaTransactionRepository extends JpaRepository<BoaTransaction, Long> {

  @Query("select boat from BoaTransaction boat where boat.amount > 0")
  List<BoaTransaction> findAllTransactions();
}
