package com.bakalov.xmlprocessing.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "categories")
public class Category extends BaseEntity implements Serializable {

  @Column(name = "name")
  private String name;

  @ManyToMany(targetEntity = Product.class, mappedBy = "categories", fetch = FetchType.EAGER)
  private List<Product> products;

  public Category() {
    this.products = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
