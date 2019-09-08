package com.bakalov.jsonparsecardealer.domains.dtos.view;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SaleViewDto implements Serializable {
  @Expose
  private Double discount;

  @Expose
  private CarViewDto car;

  public SaleViewDto() {
  }

  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public CarViewDto getCar() {
    return car;
  }

  public void setCar(CarViewDto car) {
    this.car = car;
  }
}
