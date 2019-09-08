package com.bakalov.jsonparsecardealer.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity implements Serializable {

  private String name;
  private Date birthDate;
  private Boolean isYoungDriver;
  private Set<Sale> sales;

  public Customer() {
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "birth_date")
  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  @Column(name = "is_young_driver")
  public Boolean getYoungDriver() {
    return isYoungDriver;
  }

  public void setYoungDriver(Boolean youngDriver) {
    isYoungDriver = youngDriver;
  }

  @OneToMany(mappedBy = "customer",
          targetEntity = Sale.class,
          cascade = CascadeType.ALL,
          fetch = FetchType.EAGER)
  public Set<Sale> getSales() {
    return sales;
  }

  public void setSales(Set<Sale> sales) {
    this.sales = sales;
  }
}
