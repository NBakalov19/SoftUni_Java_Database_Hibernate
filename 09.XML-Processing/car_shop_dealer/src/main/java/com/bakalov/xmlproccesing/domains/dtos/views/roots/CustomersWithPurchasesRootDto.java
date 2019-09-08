package com.bakalov.xmlproccesing.domains.dtos.views.roots;

import com.bakalov.xmlproccesing.domains.dtos.views.CustomersWithPurchasesDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersWithPurchasesRootDto implements Serializable {

  @XmlElement(name = "customer")
  private List<CustomersWithPurchasesDto> customers;

  public CustomersWithPurchasesRootDto() {
    this.customers = new ArrayList<>();
  }

  public List<CustomersWithPurchasesDto> getCustomers() {
    return customers;
  }

  public void setCustomers(List<CustomersWithPurchasesDto> customers) {
    this.customers = customers;
  }
}
