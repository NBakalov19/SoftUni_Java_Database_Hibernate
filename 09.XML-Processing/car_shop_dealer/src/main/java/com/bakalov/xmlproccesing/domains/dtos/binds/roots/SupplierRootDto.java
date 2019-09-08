package com.bakalov.xmlproccesing.domains.dtos.binds.roots;

import com.bakalov.xmlproccesing.domains.dtos.binds.SupplierSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierRootDto implements Serializable {

  @XmlElement(name = "supplier")
  private List<SupplierSeedDto> supplierSeedDtos;

  public SupplierRootDto() {
    this.supplierSeedDtos = new ArrayList<>();
  }

  public List<SupplierSeedDto> getSupplierSeedDtos() {
    return supplierSeedDtos;
  }

  public void setSupplierSeedDtos(List<SupplierSeedDto> supplierSeedDtos) {
    this.supplierSeedDtos = supplierSeedDtos;
  }
}
