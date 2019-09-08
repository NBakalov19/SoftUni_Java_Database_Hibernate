package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "districts")
public class District extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToOne(targetEntity = Town.class)
  @JoinColumn(name = "town_id", referencedColumnName = "id")
  private Town town;

  @OneToMany(targetEntity = Race.class, mappedBy = "district"/* fetch = FetchType.EAGER*/)
  private List<Race> races;

  public District() {
    this.races = new ArrayList<>();
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

  public List<Race> getRaces() {
    return races;
  }

  public void setRaces(List<Race> races) {
    this.races = races;
  }
}
