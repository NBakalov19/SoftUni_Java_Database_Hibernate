package softuni.exam.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

  @Column
  @NotNull
  @Size(min = 3, max = 20)
  private String name;

  @NotNull
  @ManyToOne(targetEntity = Picture.class)
  @JoinColumn(name = "picture_id", referencedColumnName = "id")
  private Picture picture;

  @NotNull
  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
  private Set<Player> players;

  public Team() {
    this.players = new HashSet<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Picture getPicture() {
    return picture;
  }

  public void setPicture(Picture picture) {
    this.picture = picture;
  }

  public Set<Player> getPlayers() {
    return players;
  }

  public void setPlayers(Set<Player> players) {
    this.players = players;
  }
}
