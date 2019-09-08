package com.bakalov.jsonparsecardealer.domains.dtos.view;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CarViewDto implements Serializable {

  @Expose
  @SerializedName("Make")
  private String make;

  @Expose
  @SerializedName("Model")
  private String model;

  @Expose
  @SerializedName("TravelledDistance")
  private Long travelledDistance;

  public CarViewDto() {
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
