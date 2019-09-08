package com.bakalov.xmlproccesing.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity implements Serializable {

  @Column(name = "discount")
  private BigDecimal discount;

  @OneToOne(targetEntity = Car.class)
  @JoinColumn(name = "car_id", referencedColumnName = "id")
  private Car car;

  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private Customer customer;

  public Sale() {
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
