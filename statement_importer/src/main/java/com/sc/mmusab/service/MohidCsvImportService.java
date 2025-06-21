package com.sc.mmusab.service;

import com.opencsv.CSVReader;
import com.sc.mmusab.entity.MohidTransaction;
import com.sc.mmusab.repo.MohidTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MohidCsvImportService {

  private final MohidTransactionRepository repository;

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

  public void importCsv(InputStream inputStream) throws Exception {
    try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
      String[] line;
      boolean headerSkipped = false;
      while ((line = reader.readNext()) != null) {
        if (!headerSkipped) {
          headerSkipped = true;
          continue;
        }
        if (line.length < 11 || line[1].isEmpty()) continue;

        MohidTransaction tx = MohidTransaction.builder()
            .reference(line[1])
            .donor(line[2])
            .donationDate(parseDate(line[3]))
            .donationMethod(line[4])
            .transactionType(line[5])
            .paymentMethod(line[6])
            .category(line[7])
            .recurringDate(parseDate(line[8]))
            .status(line[9])
            .amount(parseAmount(line[10]))
            .build();

        repository.save(tx);
      }
    }
  }

  private LocalDate parseDate(String date) {
    try {
      return date == null || date.isBlank() ? null : LocalDate.parse(date, formatter);
    } catch (Exception e) {
      return null;
    }
  }

  private BigDecimal parseAmount(String amount) {
    try {
      return new BigDecimal(amount.replace("$", "").replace(",", "").trim());
    } catch (Exception e) {
      return BigDecimal.ZERO;
    }
  }
}