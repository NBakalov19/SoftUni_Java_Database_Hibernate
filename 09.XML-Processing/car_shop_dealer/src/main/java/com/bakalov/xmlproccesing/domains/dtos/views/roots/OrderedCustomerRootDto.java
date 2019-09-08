package com.bakalov.xmlproccesing.domains.dtos.views.roots;

import com.bakalov.xmlproccesing.domains.dtos.views.OrderedCustomersDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomerRootDto implements Serializable {

  @XmlElement(name = "customer")
  private List<OrderedCustomersDto> customersDtoList;

  public OrderedCustomerRootDto() {
    this.customersDtoList = new ArrayList<>();
  }

  public List<OrderedCustomersDto> getCustomersDtoList() {
    return customersDtoList;
  }

  public void setCustomersDtoList(List<OrderedCustomersDto> customersDtoList) {
    this.customersDtoList = customersDtoList;
  }
}
