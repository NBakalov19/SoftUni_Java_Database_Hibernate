package com.bakalov.jsonexr.domains.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

  private String name;
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

  @ManyToMany(targetEntity = Product.class, mappedBy = "categories", fetch = FetchType.EAGER)
  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
