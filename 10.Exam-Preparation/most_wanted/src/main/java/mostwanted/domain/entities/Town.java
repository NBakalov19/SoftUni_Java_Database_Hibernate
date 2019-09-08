package mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String name;

  @OneToMany(targetEntity = District.class, mappedBy = "town"/*, fetch = FetchType.EAGER*/)
  private Set<District> districts;

  @OneToMany(targetEntity = Racer.class, mappedBy = "homeTown")
  private List<Racer> racers;

  public Town() {
    this.districts = new HashSet<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<District> getDistricts() {
    return Collections.unmodifiableSet(districts);
  }

  public void setDistricts(Set<District> districts) {
    this.districts = districts;
  }

  public List<Racer> getRacers() {
    return racers;
  }

  public void setRacers(List<Racer> racers) {
    this.racers = racers;
  }
}