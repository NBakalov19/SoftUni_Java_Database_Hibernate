package com.bakalov.jsonparsecardealer.domains.dtos.view;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class SalesDiscountsDto implements Serializable {

  @Expose
  private CarViewDto car;

  @Expose
  private String customerName;

  @Expose
  private BigDecimal discount;

  @Expose
  private BigDecimal price;

  @Expose
  private BigDecimal priceWithDiscount;

  public SalesDiscountsDto() {
  }

  public CarViewDto getCar() {
    return car;
  }

  public void setCar(CarViewDto car) {
    this.car = car;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getPriceWithDiscount() {
    return priceWithDiscount;
  }

  public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
    this.priceWithDiscount = priceWithDiscount;
  }
}
