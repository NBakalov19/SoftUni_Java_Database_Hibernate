package com.bakalov.xmlproccesing.domains.dtos.views;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesDiscountsDto implements Serializable {

  @XmlElement
  private CarViewDto car;

  @XmlElement(name = "customer-name")
  private String customerName;

  @XmlElement
  private BigDecimal discount;

  @XmlElement
  private BigDecimal price;

  @XmlElement(name = "price-with-discount")
  private BigDecimal priceWithDiscount;

  public SalesDiscountsDto() {
  }

  public CarViewDto getCar() {
    return car;
  }

  public void setCar(CarViewDto car) {
    this.car = car;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getPriceWithDiscount() {
    return priceWithDiscount;
  }

  public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
    this.priceWithDiscount = priceWithDiscount;
  }

}
