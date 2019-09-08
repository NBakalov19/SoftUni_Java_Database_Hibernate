package com.bakalov.xmlproccesing.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity implements Serializable {

  @Column(name = "name")
  private String name;

  @Column(name = "birth_date")
  private LocalDateTime birthDate;

  @Column(name = "is_young_driver")
  private Boolean isYoungDriver;

  @OneToMany(mappedBy = "customer",
          targetEntity = Sale.class,
          cascade = CascadeType.ALL,
          fetch = FetchType.EAGER)
  private Set<Sale> purchases;

  public Customer() {
    this.purchases = new HashSet<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDateTime birthDate) {
    this.birthDate = birthDate;
  }

  public Boolean getYoungDriver() {
    return isYoungDriver;
  }

  public void setYoungDriver(Boolean youngDriver) {
    isYoungDriver = youngDriver;
  }

  public Set<Sale> getPurchases() {
    return purchases;
  }

  public void setPurchases(Set<Sale> purchases) {
    this.purchases = purchases;
  }
}
