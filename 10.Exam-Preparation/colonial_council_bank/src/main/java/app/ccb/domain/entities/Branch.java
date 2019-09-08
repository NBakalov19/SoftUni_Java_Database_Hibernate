package app.ccb.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "branches")
public class Branch extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @OneToMany(targetEntity = Employee.class, mappedBy = "branch")
  private Set<Employee> employees;

  public Branch() {
    this.employees = new HashSet<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }
}
