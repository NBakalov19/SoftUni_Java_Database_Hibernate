package hiberspring.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "branches")
public class Branch extends BaseEntity {

  @Column
  @NotNull
  private String name;

  @ManyToOne(targetEntity = Town.class)
  @JoinColumn(name = "town_id", referencedColumnName = "id")
  @NotNull
  private Town town;

  @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
  private Set<Employee> employees;

  @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
  private Set<Product> products;

  public Branch() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Town getTown() {
    return town;
  }

  public void setTown(Town town) {
    this.town = town;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }
}
