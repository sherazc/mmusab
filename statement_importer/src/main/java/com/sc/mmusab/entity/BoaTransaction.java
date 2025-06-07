package com.sc.mmusab.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "BOA_TRANSACTION")
@Data
public class BoaTransaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate txnDate;

  @Column(columnDefinition = "TEXT")
  private String description;

  private BigDecimal amount;

  private BigDecimal runningBalance;

}
