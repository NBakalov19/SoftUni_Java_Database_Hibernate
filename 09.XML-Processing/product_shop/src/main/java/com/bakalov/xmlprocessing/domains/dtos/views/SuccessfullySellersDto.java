package com.bakalov.xmlprocessing.domains.dtos.views;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuccessfullySellersDto implements Serializable {

  @XmlAttribute(name = "first-name")
  private String firstName;

  @XmlAttribute(name = "last-name")
  private String lastName;

  @XmlElementWrapper(name = "sold-products")
  @XmlElement(name = "product")
  private Set<SaleDetailsDto> saleDetailDto;

  public SuccessfullySellersDto() {
    this.saleDetailDto = new HashSet<>();
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

  public Set<SaleDetailsDto> getSaleDetailDto() {
    return saleDetailDto;
  }

  public void setSaleDetailDto(Set<SaleDetailsDto> saleDetailDto) {
    this.saleDetailDto = saleDetailDto;
  }
}
