package com.bakalov.jsonexr.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

  private String firstName;
  private String lastName;
  private Integer age;
  private Set<Product> soldProducts;
  private Set<Product> boughtProducts;
  private Set<User> friends;

  public User() {
    this.soldProducts = new HashSet<>();
    this.boughtProducts = new HashSet<>();
    this.friends = new HashSet<>();
  }


  @Column(name = "first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "age")
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @OneToMany(mappedBy = "seller", targetEntity = Product.class)
  public Set<Product> getSoldProducts() {
    return soldProducts;
  }

  public void setSoldProducts(Set<Product> soldProducts) {
    this.soldProducts = soldProducts;
  }

  @OneToMany(mappedBy = "buyer", targetEntity = Product.class)
  public Set<Product> getBoughtProducts() {
    return boughtProducts;
  }

  public void setBoughtProducts(Set<Product> boughtProducts) {
    this.boughtProducts = boughtProducts;
  }

  @ManyToMany(targetEntity = User.class, cascade = CascadeType.ALL)
  @JoinTable(name = "users_friends",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
  public Set<User> getFriends() {
    return friends;
  }

  public void setFriends(Set<User> friends) {
    this.friends = friends;
  }

}
