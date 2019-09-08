package com.bakalov.xmlproccesing.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity implements Serializable {

  @Column(name = "name")
  private String name;

  @Column(name = "is_importer")
  private Boolean isImporter;

  @OneToMany(mappedBy = "supplier", targetEntity = Part.class, fetch = FetchType.EAGER)
  private List<Part> parts;

  public Supplier() {
    this.parts = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getImporter() {
    return isImporter;
  }

  public void setImporter(Boolean importer) {
    isImporter = importer;
  }

  public List<Part> getParts() {
    return parts;
  }

  public void setParts(List<Part> parts) {
    this.parts = parts;
  }
}
