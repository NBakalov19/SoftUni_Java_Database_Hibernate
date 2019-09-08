package com.bakalov.xmlprocessing.domains.dtos.views;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsDto {
  @XmlAttribute
  private Integer count;

  private Set<ProductNameAndPriceDto> soldProduct;

  public SoldProductsDto() {
    this.soldProduct = new HashSet<>();
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Set<ProductNameAndPriceDto> getSoldProduct() {
    return soldProduct;
  }

  public void setSoldProduct(Set<ProductNameAndPriceDto> soldProduct) {
    this.soldProduct = soldProduct;
  }
}
