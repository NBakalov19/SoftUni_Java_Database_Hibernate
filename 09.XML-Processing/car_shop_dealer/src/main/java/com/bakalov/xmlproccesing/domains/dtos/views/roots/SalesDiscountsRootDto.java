package com.bakalov.xmlproccesing.domains.dtos.views.roots;

import com.bakalov.xmlproccesing.domains.dtos.views.SalesDiscountsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesDiscountsRootDto {

  @XmlElement(name = "sale")
  private List<SalesDiscountsDto> sales;

  public SalesDiscountsRootDto() {
    this.sales = new ArrayList<>();
  }

  public List<SalesDiscountsDto> getSales() {
    return sales;
  }

  public void setSales(List<SalesDiscountsDto> sales) {
    this.sales = sales;
  }
}
