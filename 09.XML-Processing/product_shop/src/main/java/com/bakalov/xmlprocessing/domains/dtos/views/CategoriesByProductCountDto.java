package com.bakalov.xmlprocessing.domains.dtos.views;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;


@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductCountDto {


  @XmlAttribute
  private String name;

  @XmlElement(name = "product-count")
  private int count;

  @XmlElement(name = "average-price")
  private double averagePrice;

  @XmlElement(name = "total-revenue")
  private BigDecimal totalRevenue;

  public CategoriesByProductCountDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public double getAveragePrice() {
    return averagePrice;
  }

  public void setAveragePrice(double averagePrice) {
    this.averagePrice = averagePrice;
  }

  public BigDecimal getTotalRevenue() {
    return totalRevenue;
  }

  public void setTotalRevenue(BigDecimal totalRevenue) {
    this.totalRevenue = totalRevenue;
  }
}
