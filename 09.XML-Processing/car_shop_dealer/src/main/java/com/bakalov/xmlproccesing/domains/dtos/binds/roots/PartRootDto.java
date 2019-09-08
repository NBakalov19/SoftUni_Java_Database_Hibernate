package com.bakalov.xmlproccesing.domains.dtos.binds.roots;

import com.bakalov.xmlproccesing.domains.dtos.binds.PartSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartRootDto implements Serializable {

  @XmlElement(name = "part")
  private List<PartSeedDto> partSeedDtos;

  public PartRootDto() {
    this.partSeedDtos = new ArrayList<>();
  }

  public List<PartSeedDto> getPartSeedDtos() {
    return partSeedDtos;
  }

  public void setPartSeedDtos(List<PartSeedDto> partSeedDtos) {
    this.partSeedDtos = partSeedDtos;
  }
}
