package com.sc.mmusab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonMonth implements Comparable<PersonMonth> {
  private String fullName;
  private int month;
  private int year;

  public PersonMonth(TransactionDto transactionDto) {
    this.fullName = transactionDto.getFullName();
    if (transactionDto.getTransactionDate() != null) {
      this.month = transactionDto.getTransactionDate().getMonthValue();
      this.year = transactionDto.getTransactionDate().getYear();
    }
  }

  public String toString() {
    String monthString = String.format("%0" + 2 + "d", month);
    return String.format("%s-%s-%s", year, monthString, fullName);
  }

  public int hashCode() {
    return this.toString().hashCode();
  }

  @Override
  public int compareTo(PersonMonth o) {
    return this.toString().compareTo(o.toString());
  }

  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if  (o instanceof PersonMonth pm) {
      return this.toString().equals(pm.toString());
    } else return false;
  }
}
