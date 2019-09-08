package hiberspring.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

  @Column
  @NotNull
  private String name;

  @Column
  @Positive
  @Min(1)
  @NotNull
  private Integer population;

  @OneToMany(mappedBy = "town", cascade = CascadeType.ALL)
  private Set<Branch> branches;

  public Town() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPopulation() {
    return population;
  }

  public void setPopulation(Integer population) {
    this.population = population;
  }
}
