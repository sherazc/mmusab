package com.sc.mmusab.service;

import com.sc.mmusab.dto.PersonMonth;
import com.sc.mmusab.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class TransactionSummaryService {

  private final TransactionService transactionService;

  /**
   * Group people by month
   *
   * Create Key DTO PersonMonthDto
   *
   * Transfer TransactionDto to PersonMonthDto
   */

  public Map<String, Map<PersonMonth, Long>> getPersonYearMonthSummary(LocalDate startDate, LocalDate endDate) {
    List<TransactionDto> allTransactions = transactionService.getAllTransactions();
    Map<String, Map<PersonMonth, Long>> personSummary = new TreeMap<>();
    for (TransactionDto transaction : allTransactions) {
      Map<PersonMonth, Long> personMonthsPayments = personSummary.get(transaction.getFullName());
      if (personMonthsPayments == null) {
        personMonthsPayments = initializePersonMonthsPayments(transaction.getFullName(), startDate, endDate);
        personSummary.put(transaction.getFullName(), personMonthsPayments);
      }
      PersonMonth personMonth = new PersonMonth(transaction);
      personMonthsPayments.put(personMonth, personMonthsPayments.get(personMonth) + transaction.getAmountCents());
    }
    return personSummary;
  }

  private Map<PersonMonth, Long> initializePersonMonthsPayments(String fullName, LocalDate startDate, LocalDate endDate) {
    Map<PersonMonth, Long> personMonthsPayment = new TreeMap<>();
    if (startDate.isAfter(endDate)) {
      return personMonthsPayment;
    }

    LocalDate loopDate = startDate.plusDays(0);
    while (loopDate.isBefore(endDate)) {
      PersonMonth personMonth = PersonMonth.builder()
          .fullName(fullName)
          .month(loopDate.getMonthValue())
          .year(loopDate.getYear())
          .build();
      personMonthsPayment.put(personMonth, 0L);
      loopDate = loopDate.plusMonths(1);
    }

    return personMonthsPayment;
  }


  /**
   * Get all people who are doing recurring payments in
   */

}
