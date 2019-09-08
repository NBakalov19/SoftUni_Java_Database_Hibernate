package com.bakalov.xmlproccesing.domains.dtos.views.roots;

import com.bakalov.xmlproccesing.domains.dtos.views.CarViewDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarViewRootDto implements Serializable {

  @XmlElement(name = "car")
  private List<CarViewDto> carViewDtos;

  public CarViewRootDto() {
    this.carViewDtos = new ArrayList<>();
  }

  public List<CarViewDto> getCarViewDtos() {
    return carViewDtos;
  }

  public void setCarViewDtos(List<CarViewDto> carViewDtos) {
    this.carViewDtos = carViewDtos;
  }
}
