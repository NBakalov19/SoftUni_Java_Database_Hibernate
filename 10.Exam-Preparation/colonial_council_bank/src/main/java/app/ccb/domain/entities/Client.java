package app.ccb.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column
  private Integer age;

  @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
  @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
  private BankAccount bankAccount;

  @ManyToMany(targetEntity = Employee.class)
  @JoinTable(name = "employee_clients",
          joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
  private Set<Employee> employees;

  public Client() {
    this.employees = new HashSet<>();
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public BankAccount getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }
}
