package com.bakalov.jsonparsecardealer.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "cars")
public class Car extends BaseEntity implements Serializable {

  private String make;
  private String model;
  private Long travelledDistance;
  private List<Part> parts;

  public Car() {
  }

  @Column(name = "make")
  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  @Column(name = "model")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  @Column(name = "travelled_distance")
  public Long getTravelledDistance() {
    return travelledDistance;
  }

  public void setTravelledDistance(Long travelledDistance) {
    this.travelledDistance = travelledDistance;
  }

  @ManyToMany(targetEntity = Part.class, fetch = FetchType.EAGER)
  @JoinTable(name = "parts_cars",
          joinColumns = @JoinColumn(name = "cars_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"))
  public List<Part> getParts() {
    return parts;
  }

  public void setParts(List<Part> parts) {
    this.parts = parts;
  }
}
