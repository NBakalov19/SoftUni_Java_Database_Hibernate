package com.bakalov.xmlprocessing.domains.dtos.views;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto implements Serializable {

  @XmlAttribute(name = "first-name")
  private String firstName;

  @XmlAttribute(name = "last-name")
  private String lastName;

  @XmlAttribute
  private Integer age;


  private SoldProductsDto soldProducts;

  public UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public SoldProductsDto getSoldProducts() {
    return soldProducts;
  }

  public void setSoldProducts(SoldProductsDto soldProducts) {
    this.soldProducts = soldProducts;
  }
}
