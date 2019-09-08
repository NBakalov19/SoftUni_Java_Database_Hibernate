package entities.shampoos;

import entities.core.Size;
import entities.ingredients.BasicIngredient;
import entities.labels.BasicLabel;
import interfaces.Shampoo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public class BasicShampoo implements Shampoo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String brand;

  private BigDecimal price;

  @Enumerated
  private Size size;

  @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)//Check
  @JoinColumn(name = "label", referencedColumnName = "id")
  private BasicLabel label;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "shampoos_ingredients",
          joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
  private Set<BasicIngredient> ingredients;

  protected BasicShampoo() {
    this.ingredients = new HashSet<>();
  }

  protected BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel label) {
    this();
    this.setBrand(brand);
    this.setPrice(price);
    this.setSize(size);
    this.setLabel(label);
  }

  @Override
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  @Override
  public Size getSize() {
    return this.size;
  }

  @Override
  public void setSize(Size size) {
    this.size = size;
  }

  @Override
  public BasicLabel getLabel() {
    return this.label;
  }

  @Override
  public void setLabel(BasicLabel label) {
    this.label = label;
  }

  public Set<BasicIngredient> getIngredients() {
    return this.ingredients;
  }

  @Override
  public void setIngredients(Set<BasicIngredient> ingredients) {
    this.ingredients = ingredients;
  }
}
