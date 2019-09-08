package com.bakalov.jsonparsecardealer.domains.dtos.view;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarWithPartsDto implements Serializable {

  @Expose
  private CarViewDto car;

  @Expose
  private List<PartNameAndPriceDto> parts;

  public CarWithPartsDto() {
    this.parts = new ArrayList<>();
  }

  public CarViewDto getCar() {
    return car;
  }

  public void setCar(CarViewDto car) {
    this.car = car;
  }

  public List<PartNameAndPriceDto> getParts() {
    return parts;
  }

  public void setParts(List<PartNameAndPriceDto> parts) {
    this.parts = parts;
  }
}
