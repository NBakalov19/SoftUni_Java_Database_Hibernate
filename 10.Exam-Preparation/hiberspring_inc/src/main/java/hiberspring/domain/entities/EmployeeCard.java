package hiberspring.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees_card")
public class EmployeeCard extends BaseEntity {

  @Column(unique = true)
  @NotNull
  private String number;

  @OneToOne(mappedBy = "card")
  private Employee employee;

  public EmployeeCard() {
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Employee getEmployees() {
    return employee;
  }

  public void setEmployees(Employee employee) {
    this.employee = employee;
  }
}
