package com.bakalov.xmlproccesing.domains.dtos.binds;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;


@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartSeedDto implements Serializable {

  @XmlAttribute
  @NotNull(message = "Name can`t be null")
  @Size(min = 3, message = "Name must be at least 3 symbols long")
  private String name;

  @XmlAttribute
  @NotNull(message = "Price can`t be null")
  @Positive(message = "Price can`t be negative")
  @DecimalMin(value = "0.01", message = "Price min value must be 0.01")
  private BigDecimal price;

  @XmlAttribute
  @NotNull(message = "Quantity can`t be null")
  @Positive(message = "Quantity can`t be negative")
  @Min(value = 1, message = "Minimum quantity must be 1")
  private Integer quantity;

  public PartSeedDto() {
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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
