package com.bakalov.xmlproccesing.domains.dtos.views.roots;


import com.bakalov.xmlproccesing.domains.dtos.views.LocalSuppliersDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierRootDto {

  @XmlElement(name = "supplier")
  private List<LocalSuppliersDto> localSuppliersDtos;

  public LocalSupplierRootDto() {
    this.localSuppliersDtos = new ArrayList<>();
  }

  public List<LocalSuppliersDto> getLocalSuppliersDtos() {
    return localSuppliersDtos;
  }

  public void setLocalSuppliersDtos(List<LocalSuppliersDto> localSuppliersDtos) {
    this.localSuppliersDtos = localSuppliersDtos;
  }
}
