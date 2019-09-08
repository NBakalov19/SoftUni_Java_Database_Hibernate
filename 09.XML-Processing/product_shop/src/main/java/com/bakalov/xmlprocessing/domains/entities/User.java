package com.bakalov.xmlprocessing.domains.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "age")
  private Integer age;

  @OneToMany(mappedBy = "seller", targetEntity = Product.class, fetch = FetchType.EAGER)
  private Set<Product> soldProducts;

  @OneToMany(mappedBy = "buyer", targetEntity = Product.class, fetch = FetchType.EAGER)
  private Set<Product> boughtProducts;

  @ManyToMany(targetEntity = User.class, cascade = CascadeType.ALL)
  @JoinTable(name = "users_friends",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
  private Set<User> friends;

  public User() {
    this.soldProducts = new HashSet<>();
    this.boughtProducts = new HashSet<>();
    this.friends = new HashSet<>();
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Set<Product> getSoldProducts() {
    return soldProducts;
  }

  public void setSoldProducts(Set<Product> soldProducts) {
    this.soldProducts = soldProducts;
  }

  public Set<Product> getBoughtProducts() {
    return boughtProducts;
  }

  public void setBoughtProducts(Set<Product> boughtProducts) {
    this.boughtProducts = boughtProducts;
  }

  public Set<User> getFriends() {
    return friends;
  }

  public void setFriends(Set<User> friends) {
    this.friends = friends;
  }

}
