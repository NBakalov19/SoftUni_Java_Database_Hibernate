package footballBookmakerSystem;

import core.BaseEntityFootballDatabase;

import java.io.Serializable;

@Entity
@Table(name = "towns")
public class Town extends BaseEntityFootballDatabase implements Serializable {

  private Country country;

  public Town() {
  }

  @Override
  @Column(unique = true)
  public String getName() {
    return super.getName();
  }

  @ManyToOne(targetEntity = Country.class)
  @JoinColumn(name = "country", referencedColumnName = "id")
  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }
}
