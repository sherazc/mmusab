package com.sc.mmusab.service;

import com.sc.mmusab.dto.Source;
import com.sc.mmusab.dto.TransactionDto;
import com.sc.mmusab.entity.BoaTransaction;
import com.sc.mmusab.entity.PaypalTransaction;
import com.sc.mmusab.repo.BoaTransactionRepository;
import com.sc.mmusab.repo.PaypalTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
  private BoaTransactionRepository boaTransactionRepository;
  private PaypalTransactionRepository paypalTransactionRepository;

  public List<TransactionDto> getAllTransactions() {
    List<TransactionDto> transactions = new ArrayList<>();

    List<BoaTransaction> allBoaTransactions = boaTransactionRepository.findAllTransactions();

    transactions.addAll(getTransactionDtos(allBoaTransactions,
        "zelle payment from ", " for ", "operation"));

    transactions.addAll(getTransactionDtos(allBoaTransactions,
        "zelle recurring payment from ", " for ", "operation"));

    List<PaypalTransaction> allPaypalTransactions = paypalTransactionRepository.findAllTransactions();

    transactions.addAll(allPaypalTransactions.stream()
        .filter(t -> t.getItemTitle() != null)
        .filter(t -> t.getItemTitle().toLowerCase().contains("operation"))
        .map(this::paypalToTransactionDto)
        .toList());

    transactions.sort(Comparator.comparing(TransactionDto::getTransactionDate));

    return transactions;
  }

  private TransactionDto paypalToTransactionDto(PaypalTransaction paypalTransaction) {
    return TransactionDto.builder()
        .transactionDate(paypalTransaction.getDate())
        .payPalId(paypalTransaction.getId())
        .source(Source.PAY_PAL)
        .description(paypalTransaction.getItemTitle())
        .fullName(paypalTransaction.getName())
        .amountCents((long) (paypalTransaction.getGross().doubleValue() * 100))
        .build();

  }


  private List<TransactionDto> getTransactionDtos(List<BoaTransaction> allBoaTransactions, String nameStartPrefix, String nameEndSuffix, String containsString) {
    return allBoaTransactions.stream()
        .filter(t -> contains(t, List.of(nameStartPrefix, nameEndSuffix, containsString)))
        .filter(t -> t.getTxnDate() != null)
        .map(t -> zelleBoaToTransactionDto(t, nameStartPrefix, nameEndSuffix))
        .toList();
  }

  private TransactionDto zelleBoaToTransactionDto(BoaTransaction boaTransaction, String prefix, String suffix) {
    String description = boaTransaction.getDescription().toLowerCase();

    TransactionDto.TransactionDtoBuilder transactionDtoBuilder = TransactionDto.builder()
        .boatId(boaTransaction.getId())
        .source(Source.BOA)
        .amountCents((long) (boaTransaction.getAmount().doubleValue() * 100))
        .description(boaTransaction.getDescription())
        .transactionDate(boaTransaction.getTxnDate());

    String fullName = description.substring(prefix.length(), description.indexOf(suffix));
    transactionDtoBuilder.fullName(fullName.toUpperCase());

    return transactionDtoBuilder
        .build();
  }

  private boolean contains(BoaTransaction boaTransaction, List<String> containStrings) {
    if (boaTransaction == null || boaTransaction.getDescription() == null) return false;
    String description = boaTransaction.getDescription().toLowerCase();
    return containStrings.stream().allMatch(description::contains);

  }
}
