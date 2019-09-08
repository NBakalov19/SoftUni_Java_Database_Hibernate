package com.bakalov.jsonparsecardealer.domains.dtos.bind;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;


public class PartSeedDto implements Serializable {

  @Expose
  private String name;

  @Expose
  private BigDecimal price;

  @Expose
  private Integer quantity;

  public PartSeedDto() {
  }

  @NotNull(message = "Name can`t be null")
  @Size(min = 3, message = "Name must be at least 3 symbols long")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @NotNull(message = "Price can`t be null")
  @Positive(message = "Price can`t be negative")
  @DecimalMin(value = "0.01", message = "Price min value must be 0.01")
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @NotNull(message = "Quantity can`t be null")
  @Positive(message = "Quantity can`t be negative")
  @Min(value = 1, message = "Minimum quantity must be 1")
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
