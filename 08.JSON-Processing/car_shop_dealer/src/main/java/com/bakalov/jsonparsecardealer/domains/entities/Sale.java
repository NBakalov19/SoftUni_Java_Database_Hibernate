package com.bakalov.jsonparsecardealer.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity implements Serializable {

  private BigDecimal discount;
  private Car car;
  private Customer customer;

  public Sale() {
  }

  @Column(name = "discount")
  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  @OneToOne(targetEntity = Car.class)
  @JoinColumn(name = "car_id", referencedColumnName = "id")
  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
