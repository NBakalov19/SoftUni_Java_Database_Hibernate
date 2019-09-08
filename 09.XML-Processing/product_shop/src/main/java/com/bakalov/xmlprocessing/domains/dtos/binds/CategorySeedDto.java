package com.bakalov.xmlprocessing.domains.dtos.binds;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedDto {

  @XmlElement
  @NotNull(message = "Name can`t be null")
  @Size(min = 3, max = 15, message = "Name must be between 3 and 15 symbols")
  private String name;

  public CategorySeedDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
