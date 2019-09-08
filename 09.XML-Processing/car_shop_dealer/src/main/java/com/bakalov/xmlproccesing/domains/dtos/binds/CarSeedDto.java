package com.bakalov.xmlproccesing.domains.dtos.binds;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedDto implements Serializable {

  @XmlElement
  @NotNull(message = "Make can`t be null")
  @Size(min = 3, message = "Make must be at least 3 symbols")
  private String make;

  @XmlElement
  @NotNull(message = "Made can`t be null")
  @Size(min = 2, message = "Made must be at least 2 symbols")
  private String model;

  @XmlElement(name = "travelled-distance")
  @NotNull(message = "Travel distance can`t be null")
  @Positive(message = "Travel distance must be positive number")
  private Long travelledDistance;

  public CarSeedDto() {
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Long getTravelledDistance() {
    return travelledDistance;
  }

  public void setTravelledDistance(Long travelledDistance) {
    this.travelledDistance = travelledDistance;
  }
}
