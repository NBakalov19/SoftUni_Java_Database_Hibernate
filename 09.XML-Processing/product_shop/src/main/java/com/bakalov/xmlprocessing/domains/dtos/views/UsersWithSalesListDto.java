package com.bakalov.xmlprocessing.domains.dtos.views;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSalesListDto implements Serializable {

  @XmlAttribute(name = "count")
  private Integer userCount;


  @XmlElement(name = "user")
  private List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> soldProducts;


  public UsersWithSalesListDto() {
  }

  public Integer getUserCount() {
    return userCount;
  }

  public void setUserCount(Integer userCount) {
    this.userCount = userCount;
  }

  public List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> getSoldProducts() {
    return soldProducts;
  }

  public void setSoldProducts(List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> soldProducts) {
    this.soldProducts = soldProducts;
  }
}
