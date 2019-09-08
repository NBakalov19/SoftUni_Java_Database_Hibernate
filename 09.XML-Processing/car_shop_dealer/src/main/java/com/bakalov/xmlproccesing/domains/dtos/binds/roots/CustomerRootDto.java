package com.bakalov.xmlproccesing.domains.dtos.binds.roots;

import com.bakalov.xmlproccesing.domains.dtos.binds.CustomerSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRootDto implements Serializable {

  @XmlElement(name = "customer")
  private List<CustomerSeedDto> customerSeedDtos;

  public CustomerRootDto() {
    this.customerSeedDtos = new ArrayList<>();
  }

  public List<CustomerSeedDto> getCustomerSeedDtos() {
    return customerSeedDtos;
  }

  public void setCustomerSeedDtos(List<CustomerSeedDto> customerSeedDtos) {
    this.customerSeedDtos = customerSeedDtos;
  }
}
