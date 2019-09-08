package com.bakalov.jsonparsecardealer.domains.dtos.bind;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CarSeedDto implements Serializable {

  @Expose
  private String make;

  @Expose
  private String model;

  @Expose
  private Long travelledDistance;

  public CarSeedDto() {
  }

  @NotNull(message = "Make can`t be null")
  @Size(min = 3, message = "Make must be at least 3 symbols")
  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  @NotNull(message = "Made can`t be null")
  @Size(min = 2, message = "Made must be at least 2 symbols")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  @NotNull(message = "Travel distance can`t be null")
  @Positive(message = "Travel distance must be positive number")
  public Long getTravelledDistance() {
    return travelledDistance;
  }

  public void setTravelledDistance(Long travelledDistance) {
    this.travelledDistance = travelledDistance;
  }
}
