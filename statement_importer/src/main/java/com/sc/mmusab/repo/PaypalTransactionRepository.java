package com.sc.mmusab.repo;

import com.sc.mmusab.entity.PaypalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaypalTransactionRepository extends JpaRepository<PaypalTransaction, Long> {
  @Query(value = """
      select ppt from PaypalTransaction ppt
      where trim(ppt.name) != ''
      and ppt.gross > 0
     """)
  List<PaypalTransaction> findAllTransactions();
}