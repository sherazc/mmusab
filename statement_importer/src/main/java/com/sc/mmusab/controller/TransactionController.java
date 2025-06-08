package com.sc.mmusab.controller;

import com.sc.mmusab.dto.PersonMonth;
import com.sc.mmusab.dto.TransactionDto;
import com.sc.mmusab.service.TransactionService;
import com.sc.mmusab.service.TransactionSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

  private final TransactionService transactionService;
  private final TransactionSummaryService transactionSummaryService;

  @GetMapping
  public List<TransactionDto> getAllTransactions(){
    return transactionService.getAllTransactions();
  }

  @GetMapping("/summary")
  public Map<String, Map<PersonMonth, Long>> getPersonYearMonthSummary(
      @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
    return  transactionSummaryService.getPersonYearMonthSummary(startDate, endDate);
  }
}
