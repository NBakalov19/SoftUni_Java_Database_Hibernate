package com.bakalov.jsonexr.domains.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;


public class ProductSeedDto implements Serializable {

  @Expose
  private String name;

  @Expose
  private BigDecimal price;


  public ProductSeedDto() {
  }

  @NotNull(message = "Name can`t be null")
  @Size(min = 3, message = "Name should be at least 3 symbols")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @NotNull(message = "Price can`t be null")
  @Positive(message = "Price must be positive number")
  @DecimalMin(value = "0.01", message = "Price must be 0.01")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}
