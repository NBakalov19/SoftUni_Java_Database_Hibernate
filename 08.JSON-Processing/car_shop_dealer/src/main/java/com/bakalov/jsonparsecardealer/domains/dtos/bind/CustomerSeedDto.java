package com.bakalov.jsonparsecardealer.domains.dtos.bind;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class CustomerSeedDto implements Serializable {

  @Expose
  private String name;

  @Expose
  private Date birthDate;

  @Expose
  private Boolean isYoungDriver;

  public CustomerSeedDto() {
  }

  @NotNull(message = "Name can`t be null")
  @Size(min = 3, message = "Name must be at least 3 symbols")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @NotNull(message = "Birth date can`t be null")
  @Past(message = "Birth date can`t be in future")
  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  @NotNull(message = "Young driver can`t be null")
  public Boolean getYoungDriver() {
    return isYoungDriver;
  }

  public void setYoungDriver(Boolean youngDriver) {
    isYoungDriver = youngDriver;
  }
}
