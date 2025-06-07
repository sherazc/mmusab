package com.sc.mmusab.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "paypal_transaction")
@Data
public class PaypalTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate date;
  private LocalTime time;
  private String timeZone;
  private String name;
  private String type;
  private String status;
  private String currency;

  private BigDecimal gross;
  private BigDecimal fee;
  private BigDecimal net;

  @Column(name = "from_email")
  private String fromEmail;

  @Column(name = "to_email")
  private String toEmail;

  @Column(name = "transaction_id")
  private String transactionId;

  @Column(length = 1000)
  private String shippingAddress;

  private String addressStatus;

  @Column(length = 1000)

  private String itemTitle;

  private String itemId;

  private BigDecimal shippingAndHandlingAmount;
  private BigDecimal insuranceAmount;
  private BigDecimal salesTax;

  @Column(name = "option1_name")
  private String option1Name;
  @Column(name = "option1_value")
  private String option1Value;
  @Column(name = "option2_name")
  private String option2Name;
  @Column(name = "option2_value")
  private String option2Value;

  private String referenceTxnId;
  private String invoiceNumber;
  private String customNumber;
  private String quantity;
  private String receiptId;

  private BigDecimal balance;

  private String addressLine1;
  private String addressLine2;
  private String city;
  private String state;
  private String postalCode;
  private String country;

  private String phone;

  @Column(length = 1000)
  private String subject;

  @Column(length = 1000)
  private String note;

  private String countryCode;
  private String balanceImpact;
}