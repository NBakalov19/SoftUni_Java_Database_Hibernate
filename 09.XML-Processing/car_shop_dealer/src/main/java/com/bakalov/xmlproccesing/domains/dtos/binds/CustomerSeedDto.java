package com.bakalov.xmlproccesing.domains.dtos.binds;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedDto implements Serializable {

  @XmlAttribute
  @NotNull(message = "Name can`t be null")
  @Size(min = 3, message = "Name must be at least 3 symbols")
  private String name;

  @XmlElement(name = "birth-date")
  @NotNull(message = "Birth date can`t be null")
  @Past(message = "Birth date can`t be in future")
  private LocalDateTime birthDate;

  @XmlElement(name = "is-young-driver")
  @NotNull(message = "Young driver can`t be null")
  private Boolean isYoungDriver;

  public CustomerSeedDto() {
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
