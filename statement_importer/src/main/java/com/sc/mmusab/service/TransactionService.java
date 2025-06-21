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

    List<BoaTransaction> allBoaZelle = allBoaTransactions.stream()
        .filter(t -> t.getDescription() != null)
        .filter(t -> t.getDescription().toLowerCase().startsWith("zelle"))
        .toList();

    List<BoaTransaction> allBoaZelleWithFor = allBoaZelle.stream()
        .filter(t -> t.getDescription().toLowerCase().contains(" for "))
        .toList();

    // Sometimes Zelle do not contain " for ". In that case person name ends at " Conf#"
    List<BoaTransaction> allBoaZelleWithoutFor = allBoaZelle.stream()
        .filter(t -> !t.getDescription().toLowerCase().contains(" for "))
        .toList();

    List<String> blackListDescriptionTokens = List.of();

    transactions.addAll(getTransactionDtos(allBoaZelleWithFor,
        "zelle payment from ", " for ", blackListDescriptionTokens));

    transactions.addAll(getTransactionDtos(allBoaZelleWithFor,
        "zelle recurring payment from ", " for ", blackListDescriptionTokens));

    transactions.addAll(getTransactionDtos(allBoaZelleWithoutFor,
        "zelle payment from ", " conf#", blackListDescriptionTokens));

    transactions.addAll(getTransactionDtos(allBoaZelleWithoutFor,
        "zelle recurring payment from ", " conf#", blackListDescriptionTokens));

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
        .fullName(paypalTransaction.getName() == null ? "" : paypalTransaction.getName().toUpperCase())
        .amountCents((long) (paypalTransaction.getGross().doubleValue() * 100))
        .build();

  }


  private List<TransactionDto> getTransactionDtos(List<BoaTransaction> allBoaTransactions, String nameStartPrefix, String nameEndSuffix, List<String> blackListDescriptionTokens) {
    return allBoaTransactions.stream()
        .filter(t -> contains(t, List.of(nameStartPrefix, nameEndSuffix)))
        .filter(t -> t.getTxnDate() != null)
        .filter(t -> t.getDescription() != null)
        .filter(t -> notContainBlackListTokens(t.getDescription(), blackListDescriptionTokens))
        .map(t -> zelleBoaToTransactionDto(t, nameStartPrefix, nameEndSuffix))
        .toList();
  }

  private boolean notContainBlackListTokens(String description, List<String> blackListDescriptionTokens) {
    return blackListDescriptionTokens.stream().noneMatch(token ->
        description.toLowerCase().contains(token.toLowerCase()));
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
