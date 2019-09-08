package core;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntityFootballDatabase extends BaseEntity {
  private String name;


  protected BaseEntityFootballDatabase() {
    super();
  }

  @Column(nullable = false, length = 50)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
