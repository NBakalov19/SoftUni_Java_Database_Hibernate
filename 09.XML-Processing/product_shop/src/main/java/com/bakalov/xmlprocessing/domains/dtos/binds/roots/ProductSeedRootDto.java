package com.bakalov.xmlprocessing.domains.dtos.binds.roots;

import com.bakalov.xmlprocessing.domains.dtos.binds.ProductSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDto implements Serializable {

  @XmlElement(name = "product")
  private List<ProductSeedDto> productSeedDtos;

  public ProductSeedRootDto() {
    this.productSeedDtos = new ArrayList<>();
  }

  public List<ProductSeedDto> getProductSeedDtos() {
    return productSeedDtos;
  }

  public void setProductSeedDtos(List<ProductSeedDto> productSeedDtos) {
    this.productSeedDtos = productSeedDtos;
  }
}