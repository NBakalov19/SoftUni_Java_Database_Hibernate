package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportDto implements Serializable {

  @XmlAttribute
  private String name;

  @XmlAttribute
  private Integer clients;

  @XmlElement
  private String branch;

  public ProductImportDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getClients() {
    return clients;
  }

  public void setClients(Integer clients) {
    this.clients = clients;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }
}
