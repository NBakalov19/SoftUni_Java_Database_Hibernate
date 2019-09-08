package com.bakalov.xmlproccesing.domains.dtos.views;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersWithPurchasesDto implements Serializable {

  @XmlAttribute(name = "full-name")
  private String fullName;

  @XmlAttribute(name = "bought-cars")
  private Integer boughtCars;

  @XmlAttribute(name = "spend-money")
  private BigDecimal spentMoney;

  public CustomersWithPurchasesDto() {
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Integer getBoughtCars() {
    return boughtCars;
  }

  public void setBoughtCars(Integer boughtCars) {
    this.boughtCars = boughtCars;
  }

  public BigDecimal getSpentMoney() {
    return spentMoney;
  }

  public void setSpentMoney(BigDecimal spentMoney) {
    this.spentMoney = spentMoney;
  }
}
