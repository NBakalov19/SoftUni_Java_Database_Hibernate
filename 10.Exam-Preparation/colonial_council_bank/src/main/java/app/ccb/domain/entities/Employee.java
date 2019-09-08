package app.ccb.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column
  private BigDecimal salary;

  @Column
  private LocalDate startedOn;

  @ManyToOne(targetEntity = Branch.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false)
  private Branch branch;

  @ManyToMany(targetEntity = Client.class, mappedBy = "employees")
  private Set<Client> clients;

  public Employee() {
    this.clients = new HashSet<>();
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

  public LocalDate getStartedOn() {
    return startedOn;
  }

  public void setStartedOn(LocalDate startedOn) {
    this.startedOn = startedOn;
  }

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public Set<Client> getClients() {
    return clients;
  }

  public void setClients(Set<Client> clients) {
    this.clients = clients;
  }
}
