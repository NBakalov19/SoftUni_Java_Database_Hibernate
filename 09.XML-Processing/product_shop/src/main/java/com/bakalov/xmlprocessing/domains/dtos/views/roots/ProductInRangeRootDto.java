package com.bakalov.xmlprocessing.domains.dtos.views.roots;

import com.bakalov.xmlprocessing.domains.dtos.views.ProductInRangeDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInRangeRootDto implements Serializable {

  @XmlElement(name = "product")
  private List<ProductInRangeDto> products;

  public ProductInRangeRootDto() {
  }

  public List<ProductInRangeDto> getProducts() {
    return products;
  }

  public void setProducts(List<ProductInRangeDto> products) {
    this.products = products;
  }
}
