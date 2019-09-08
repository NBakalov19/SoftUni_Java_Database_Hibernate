package footballBookmakerSystem;

import core.BaseEntityFootballDatabase;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent extends BaseEntityFootballDatabase implements Serializable {

  private Set<Country> countries;

  public Continent() {
  }

  @ManyToMany(mappedBy = "continents")
  public Set<Country> getCountries() {
    return countries;
  }

  public void setCountries(Set<Country> countries) {
    this.countries = countries;
  }
}
