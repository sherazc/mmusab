package com.sc.mmusab.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "MOHID_TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MohidTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String reference;

  private String donor;

  @Column(name = "donation_date")
  private LocalDate donationDate;

  @Column(name = "donation_method")
  private String donationMethod;

  @Column(name = "transaction_type")
  private String transactionType;

  @Column(name = "payment_method")
  private String paymentMethod;

  private String category;

  @Column(name = "recurring_date")
  private LocalDate recurringDate;

  private String status;

  private BigDecimal amount;
}