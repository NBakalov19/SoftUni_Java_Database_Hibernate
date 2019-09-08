package softuni.exam.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

  @Column
  @NotNull
  private String url;

  @OneToMany(mappedBy = "picture", cascade = CascadeType.ALL)
  private Set<Team> teams;

  @OneToMany(mappedBy = "picture", cascade = CascadeType.ALL)
  private Set<Player> players;

  public Picture() {
    this.teams = new HashSet<>();
    this.players = new HashSet<>();
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Set<Team> getTeams() {
    return teams;
  }

  public void setTeams(Set<Team> teams) {
    this.teams = teams;
  }

  public Set<Player> getPlayers() {
    return players;
  }

  public void setPlayers(Set<Player> players) {
    this.players = players;
  }
}
