package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {

  @Column(name = "name", nullable = false, unique = true)
  @Size(min = 3, max = 30)
  private String name;

  @ManyToOne(targetEntity = Category.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
  private Category category;

  @Column(name = "price", nullable = false)
  @Positive
  @DecimalMin(value = "0.01")
  private BigDecimal price;

  @OneToMany(mappedBy = "item")
  private List<OrderItem> orderItems;

  public Item() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }
}
