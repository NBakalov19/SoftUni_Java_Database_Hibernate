package com.bakalov.jsonparsecardealer.domains.dtos.view;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderedCustomersDto implements Serializable {

  @Expose
  @SerializedName("Id")
  private Integer id;
  @Expose
  @SerializedName("Name")
  private String name;
  @Expose
  @SerializedName("BirthDate")
  private Date birthDate;
  @Expose
  @SerializedName("IsYoungDriver")
  private Boolean isYoungDriver;
  @Expose
  @SerializedName("Sales")
  private Set<SaleViewDto> sales;

  public OrderedCustomersDto() {
    this.sales = new HashSet<>();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Boolean getYoungDriver() {
    return isYoungDriver;
  }

  public void setYoungDriver(Boolean youngDriver) {
    isYoungDriver = youngDriver;
  }

  public Set<SaleViewDto> getSales() {
    return sales;
  }

  public void setSales(Set<SaleViewDto> sales) {
    this.sales = sales;
  }
}
