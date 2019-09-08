package hiberspring.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

  @Column(name = "first_name")
  @NotNull
  private String firstName;

  @Column(name = "last_name")
  @NotNull
  private String lastName;

  @Column
  @NotNull
  private String position;

  @OneToOne(targetEntity = EmployeeCard.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "employee_card_id", referencedColumnName = "id")
  @NotNull
  private EmployeeCard card;

  @ManyToOne(targetEntity = Branch.class)
  @JoinColumn(name = "branch_id", referencedColumnName = "id")
  @NotNull
  private Branch branch;

  public Employee() {
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

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public EmployeeCard getCard() {
    return card;
  }

  public void setCard(EmployeeCard card) {
    this.card = card;
  }

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }
}
