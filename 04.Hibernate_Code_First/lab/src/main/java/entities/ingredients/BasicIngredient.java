package entities.ingredients;

import entities.shampoos.BasicShampoo;
import interfaces.Ingredient;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ingredients")
@DiscriminatorColumn(name = "ingredient_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicIngredient implements Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private BigDecimal price;

  @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.ALL)
  private List<BasicShampoo> shampoos;

  protected BasicIngredient() {
  }

  protected BasicIngredient(String name, BigDecimal price) {
    this.setName(name);
    this.setPrice(price);
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public BigDecimal getPrice() {
    return this.price;
  }

  @Override
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public List<BasicShampoo> getShampoos() {
    return this.shampoos;
  }

  @Override
  public void setShampoos(List<BasicShampoo> shampoos) {
    this.shampoos = shampoos;
  }
}