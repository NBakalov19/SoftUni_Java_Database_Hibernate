package com.bakalov.jsonparsecardealer.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity implements Serializable {

  private String name;
  private Boolean isImporter;
  private Set<Part> parts;

  public Supplier() {
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "is_importer")
  public Boolean getImporter() {
    return isImporter;
  }

  public void setImporter(Boolean importer) {
    isImporter = importer;
  }

  @OneToMany(mappedBy = "supplier", targetEntity = Part.class, fetch = FetchType.EAGER)
  public Set<Part> getParts() {
    return parts;
  }

  public void setParts(Set<Part> parts) {
    this.parts = parts;
  }
}
