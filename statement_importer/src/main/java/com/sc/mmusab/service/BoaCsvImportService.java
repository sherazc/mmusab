package com.sc.mmusab.service;

import com.opencsv.CSVReader;
import com.sc.mmusab.entity.BoaStatementSummary;
import com.sc.mmusab.entity.BoaTransaction;
import com.sc.mmusab.repo.BoaStatementSummaryRepository;
import com.sc.mmusab.repo.BoaTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BoaCsvImportService {

  @Autowired
  private BoaStatementSummaryRepository summaryRepo;

  @Autowired
  private BoaTransactionRepository txnRepo;

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

  public void importCsv(InputStream inputStream) throws Exception {
    try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
      List<String[]> lines = reader.readAll();
      boolean isTransactionSection = false;

      for (int i = 1; i < lines.size(); i++) {
        String[] row = lines.get(i);
        if (row.length < 2) continue;

        if (row[0].equalsIgnoreCase("Date")) {
          isTransactionSection = true;
          continue;
        }

        if (!isTransactionSection) {
          String desc = row[0].trim();
          BigDecimal amount = getAmount(row[2]);

          BoaStatementSummary summary = new BoaStatementSummary();
          summary.setDescription(desc);
          summary.setSummaryAmount(amount);
          summaryRepo.save(summary);

        } else {
          if (row[0].isEmpty()) continue;

          LocalDate date = LocalDate.parse(row[0], FORMATTER);
          String desc = row[1].replaceAll("\"", "");
          BigDecimal amount = getAmount(row[2]);
          BigDecimal balance = getAmount(row[3]);

          BoaTransaction txn = new BoaTransaction();
          txn.setTxnDate(date);
          txn.setDescription(desc);
          txn.setAmount(amount);
          txn.setRunningBalance(balance);
          txnRepo.save(txn);
        }
      }
    }
  }

  private static BigDecimal getAmount(String numString) {
    if (numString == null || numString.isEmpty()) return null;
    String numStringCleaned = numString.trim().replaceAll("\"", "").replace(",", "");
    try {
      return new BigDecimal(numStringCleaned);
    } catch (Exception e) {
      return null;
    }
  }
}

