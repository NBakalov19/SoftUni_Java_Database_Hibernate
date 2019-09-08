package com.bakalov.jsonexr.domains.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SuccessfullySellersDto implements Serializable {

  @Expose
  private String firstName;

  @Expose
  private String lastName;

  @Expose
  private Set<ProductNamePriceAndBuyerNamesDto> soldProducts;

  public SuccessfullySellersDto() {
    this.soldProducts = new HashSet<>();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Set<ProductNamePriceAndBuyerNamesDto> getSoldProducts() {
    return soldProducts;
  }

  public void setSoldProducts(Set<ProductNamePriceAndBuyerNamesDto> soldProducts) {
    this.soldProducts = soldProducts;
  }
}
