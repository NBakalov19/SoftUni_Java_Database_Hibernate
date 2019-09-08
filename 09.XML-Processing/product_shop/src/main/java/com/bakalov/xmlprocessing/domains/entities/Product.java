package com.bakalov.xmlprocessing.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity implements Serializable {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "price")
  private BigDecimal price;

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
  private User seller;

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "buyer_id", referencedColumnName = "id")
  private User buyer;

  @ManyToMany(targetEntity = Category.class, cascade = CascadeType.ALL)
  @JoinTable(name = "category_products",
          joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
  private List<Category> categories;

  public Product() {
    this.categories = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public User getSeller() {
    return seller;
  }

  public void setSeller(User seller) {
    this.seller = seller;
  }

  public User getBuyer() {
    return buyer;
  }

  public void setBuyer(User buyer) {
    this.buyer = buyer;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
