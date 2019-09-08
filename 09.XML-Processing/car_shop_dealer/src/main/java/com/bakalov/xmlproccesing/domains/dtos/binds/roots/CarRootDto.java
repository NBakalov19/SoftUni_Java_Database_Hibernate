package com.bakalov.xmlproccesing.domains.dtos.binds.roots;

import com.bakalov.xmlproccesing.domains.dtos.binds.CarSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRootDto implements Serializable {

  @XmlElement(name = "car")
  private List<CarSeedDto> carSeedDtos;

  public CarRootDto() {
    this.carSeedDtos = new ArrayList<>();
  }

  public List<CarSeedDto> getCarSeedDtos() {
    return carSeedDtos;
  }

  public void setCarSeedDtos(List<CarSeedDto> carSeedDtos) {
    this.carSeedDtos = carSeedDtos;
  }
}
