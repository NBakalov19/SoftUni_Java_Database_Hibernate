package com.bakalov.xmlprocessing.domains.dtos.binds;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedDto implements Serializable {

  @XmlElement
  @NotNull(message = "Name can`t be null")
  @Size(min = 3, message = "Name should be at least 3 symbols")
  private String name;

  @XmlElement
  @NotNull(message = "Price can`t be null")
  @Positive(message = "Price must be positive number")
  @DecimalMin(value = "0.01", message = "Price must be 0.01")
  private BigDecimal price;

  public ProductSeedDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
