package com.bakalov.xmlproccesing.domains.dtos.binds;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedDto implements Serializable {

  @XmlAttribute
  @NotNull(message = "Name can`t be null")
  @Size(min = 2, message = "Name must be t least 2 symbols long")
  private String name;

  @XmlAttribute(name = "is-importer")
  @NotNull(message = "Importer can`t be null")
  private Boolean isImporter;

  public SupplierSeedDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getImporter() {
    return isImporter;
  }

  public void setImporter(Boolean importer) {
    isImporter = importer;
  }
}