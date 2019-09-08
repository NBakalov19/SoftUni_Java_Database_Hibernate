package com.bakalov.xmlproccesing.domains.dtos.views;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWithPartsDto implements Serializable {

  @XmlAttribute
  private Integer id;

  @XmlAttribute
  private String make;

  @XmlAttribute
  private String model;

  @XmlAttribute(name = "travelled-distance")
  private Long travelledDistance;


  @XmlElementWrapper(name = "parts")
  @XmlElement(name = "car")
  private Set<PartNameAndPriceDto> partNameAndPriceDtoSet;

  public CarsWithPartsDto() {
    this.partNameAndPriceDtoSet = new HashSet<>();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Set<PartNameAndPriceDto> getPartNameAndPriceDtoSet() {
    return partNameAndPriceDtoSet;
  }

  public void setPartNameAndPriceDtoSet(Set<PartNameAndPriceDto> partNameAndPriceDtoSet) {
    this.partNameAndPriceDtoSet = partNameAndPriceDtoSet;
  }
}
