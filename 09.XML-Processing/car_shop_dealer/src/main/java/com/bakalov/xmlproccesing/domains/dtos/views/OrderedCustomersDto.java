package com.bakalov.xmlproccesing.domains.dtos.views;

import com.bakalov.xmlproccesing.utils.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;


@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomersDto {

  @XmlElement(name = "id")
  private Integer id;

  @XmlElement
  private String name;

  @XmlElement(name = "birth-date")
  @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
  private LocalDateTime birthDate;

  @XmlElement(name = "is-young-driver")
  private Boolean isYoungDriver;

  public OrderedCustomersDto() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDateTime birthDate) {
    this.birthDate = birthDate;
  }

  public Boolean getYoungDriver() {
    return isYoungDriver;
  }

  public void setYoungDriver(Boolean youngDriver) {
    isYoungDriver = youngDriver;
  }
}
