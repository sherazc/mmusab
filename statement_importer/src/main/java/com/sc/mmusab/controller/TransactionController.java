package com.sc.mmusab.controller;

import com.sc.mmusab.dto.TransactionDto;
import com.sc.mmusab.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  @GetMapping
  public List<TransactionDto> getAllTransactions(){
    return transactionService.getAllTransactions();
  }
}
