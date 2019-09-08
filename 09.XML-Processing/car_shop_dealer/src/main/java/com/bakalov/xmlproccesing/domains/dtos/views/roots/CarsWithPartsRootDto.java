package com.bakalov.xmlproccesing.domains.dtos.views.roots;

import com.bakalov.xmlproccesing.domains.dtos.views.CarsWithPartsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWithPartsRootDto implements Serializable {

  @XmlElement(name = "car")
  private List<CarsWithPartsDto> carsWithPartsDtos;

  public CarsWithPartsRootDto() {
    this.carsWithPartsDtos = new ArrayList<>();
  }

  public List<CarsWithPartsDto> getCarsWithPartsDtos() {
    return carsWithPartsDtos;
  }

  public void setCarsWithPartsDtos(List<CarsWithPartsDto> carsWithPartsDtos) {
    this.carsWithPartsDtos = carsWithPartsDtos;
  }
}
