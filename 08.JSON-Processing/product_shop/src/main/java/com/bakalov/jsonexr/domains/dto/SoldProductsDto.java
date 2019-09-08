package com.bakalov.jsonexr.domains.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class SoldProductsDto implements Serializable {

  @Expose
  private Integer count;

  @Expose
  private Set<ProductNameAndPriceDto> soldProducts;

  public SoldProductsDto() {
    this.soldProducts = new HashSet<>();
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Set<ProductNameAndPriceDto> getSoldProducts() {
    return soldProducts;
  }

  public void setSoldProducts(Set<ProductNameAndPriceDto> soldProducts) {
    this.soldProducts = soldProducts;
  }
}
