package com.sc.mmusab.service;

import com.opencsv.CSVReader;
import com.sc.mmusab.entity.PaypalTransaction;
import com.sc.mmusab.repo.PaypalTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaypalTransactionService {

  private final PaypalTransactionRepository repository;

  public void importCSV(MultipartFile file) throws Exception {
    List<PaypalTransaction> transactions = new ArrayList<>();
    try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
      String[] line;
      reader.readNext(); // skip header
      while ((line = reader.readNext()) != null) {
        PaypalTransaction txn = new PaypalTransaction();
        txn.setDate(LocalDate.parse(line[0], DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        txn.setTime(LocalTime.parse(line[1]));
        txn.setTimeZone(line[2]);
        txn.setName(line[3]);
        txn.setType(line[4]);
        txn.setStatus(line[5]);
        txn.setCurrency(line[6]);
        txn.setGross(parseBigDecimal(line[7]));
        txn.setFee(parseBigDecimal(line[8]));
        txn.setNet(parseBigDecimal(line[9]));
        txn.setFromEmail(line[10]);
        txn.setToEmail(line[11]);
        txn.setTransactionId(line[12]);
        txn.setShippingAddress(line[13]);
        txn.setAddressStatus(line[14]);
        txn.setItemTitle(line[15]);
        txn.setItemId(line[16]);
        txn.setShippingAndHandlingAmount(parseBigDecimal(line[17]));
        txn.setInsuranceAmount(parseBigDecimal(line[18]));
        txn.setSalesTax(parseBigDecimal(line[19]));
        txn.setOption1Name(line[20]);
        txn.setOption1Value(line[21]);
        txn.setOption2Name(line[22]);
        txn.setOption2Value(line[23]);
        txn.setReferenceTxnId(line[24]);
        txn.setInvoiceNumber(line[25]);
        txn.setCustomNumber(line[26]);
        txn.setQuantity(line[27]);
        txn.setReceiptId(line[28]);
        txn.setBalance(parseBigDecimal(line[29]));
        txn.setAddressLine1(line[30]);
        txn.setAddressLine2(line[31]);
        txn.setCity(line[32]);
        txn.setState(line[33]);
        txn.setPostalCode(line[34]);
        txn.setCountry(line[35]);
        txn.setPhone(line[36]);
        txn.setSubject(line[37]);
        txn.setNote(line[38]);
        txn.setCountryCode(line[39]);
        txn.setBalanceImpact(line[40]);
        transactions.add(txn);
      }
      repository.saveAll(transactions);
    }
  }

  private BigDecimal parseBigDecimal(String value) {
    if (value == null || value.isBlank()) return null;
    return new BigDecimal(value.replace(",", ""));
  }
}