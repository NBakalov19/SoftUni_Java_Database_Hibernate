package com.bakalov.jsonexr.domains.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductNameAndPriceDto implements Serializable {

  @Expose
  private String name;

  @Expose
  private BigDecimal price;

  public ProductNameAndPriceDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
