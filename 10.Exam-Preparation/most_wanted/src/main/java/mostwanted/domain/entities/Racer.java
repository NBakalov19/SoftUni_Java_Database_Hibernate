package mostwanted.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "racers")
public class Racer extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String name;

  @Column
  private Integer age;

  @Column
  private BigDecimal bounty;

  @ManyToOne(targetEntity = Town.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "town_id", referencedColumnName = "id")
  private Town homeTown;

  @OneToMany(targetEntity = Car.class, mappedBy = "racer")
  private List<Car> cars;

  public Racer() {
    this.cars = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public BigDecimal getBounty() {
    return bounty;
  }

  public void setBounty(BigDecimal bounty) {
    this.bounty = bounty;
  }

  public Town getHomeTown() {
    return homeTown;
  }

  public void setHomeTown(Town homeTown) {
    this.homeTown = homeTown;
  }

  public List<Car> getCars() {
    return Collections.unmodifiableList(cars);
  }

  public void setCars(List<Car> cars) {
    this.cars = cars;
  }
}
