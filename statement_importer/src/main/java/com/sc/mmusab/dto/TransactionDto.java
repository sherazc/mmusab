package com.sc.mmusab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
  private Long boaId;
  private Long payPalId;
  private Long mohidId;
  private Source source;
  private String fullName;
  private String description;
  private Long amountCents;
  private LocalDate transactionDate;
}
