package com.bakalov.jsonparsecardealer.domains.dtos.view;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomersWithPurchasesDto implements Serializable {

  @Expose
  private String fullName;

  @Expose
  private Integer boughtCars;

  @Expose
  private BigDecimal spentMoney;

  public CustomersWithPurchasesDto() {
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Integer getBoughtCars() {
    return boughtCars;
  }

  public void setBoughtCars(Integer boughtCars) {
    this.boughtCars = boughtCars;
  }

  public BigDecimal getSpentMoney() {
    return spentMoney;
  }

  public void setSpentMoney(BigDecimal spentMoney) {
    this.spentMoney = spentMoney;
  }
}
