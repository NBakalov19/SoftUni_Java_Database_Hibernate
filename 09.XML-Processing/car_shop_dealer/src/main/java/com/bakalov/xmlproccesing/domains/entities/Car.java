package com.bakalov.xmlproccesing.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cars")
public class Car extends BaseEntity implements Serializable {

  @Column(name = "make", updatable = false)
  private String make;

  @Column(name = "model", updatable = false)
  private String model;

  @Column(name = "travelled_distance")
  private Long travelledDistance;

  @ManyToMany(targetEntity = Part.class, fetch = FetchType.EAGER)
  @JoinTable(name = "parts_cars",
          joinColumns = @JoinColumn(name = "cars_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"))
  private List<Part> parts;

  public Car() {
    this.parts = new ArrayList<>();
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

  public List<Part> getParts() {
    return parts;
  }

  public void setParts(List<Part> parts) {
    this.parts = parts;
  }
}
