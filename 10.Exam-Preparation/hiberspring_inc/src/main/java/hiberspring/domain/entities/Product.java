package hiberspring.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

  @Column
  @NotNull
  private String name;

  @Column
  @NotNull
  private Integer clients;

  @ManyToOne(targetEntity = Branch.class)
  @JoinColumn(name = "branch_id", referencedColumnName = "id")
  @NotNull
  private Branch branch;

  public Product() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getClients() {
    return clients;
  }

  public void setClients(Integer clients) {
    this.clients = clients;
  }

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }
}
