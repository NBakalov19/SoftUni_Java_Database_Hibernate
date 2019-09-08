package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportRootDto implements Serializable {

  @XmlElement(name = "product")
  private List<ProductImportDto> productImportDtos;

  public ProductImportRootDto() {
    this.productImportDtos = new ArrayList<>();
  }

  public List<ProductImportDto> getProductImportDtos() {
    return productImportDtos;
  }

  public void setProductImportDtos(List<ProductImportDto> productImportDtos) {
    this.productImportDtos = productImportDtos;
  }
}