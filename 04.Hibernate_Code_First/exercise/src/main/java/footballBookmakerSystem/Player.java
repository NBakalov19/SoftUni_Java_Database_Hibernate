package footballBookmakerSystem;

import core.BaseEntityFootballDatabase;

import java.io.Serializable;

@Entity
@Table(name = "players")
public class Player extends BaseEntityFootballDatabase implements Serializable {

  private byte squadNumber;
  private Team team;
  private Position position;
  private boolean isCurrentlyInjured;

  public Player() {
  }

  @Column(name = "squad_number")
  public byte getSquadNumber() {
    return squadNumber;
  }

  public void setSquadNumber(byte squadNumber) {
    this.squadNumber = squadNumber;
  }

  @ManyToOne(targetEntity = Team.class)
  @JoinColumn(name = "team_id", referencedColumnName = "id")
  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @ManyToOne(targetEntity = Position.class)
  @JoinColumn(name = "position_id", referencedColumnName = "id")
  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  @Column(name = "is_injured")
  public boolean isCurrentlyInjured() {
    return isCurrentlyInjured;
  }

  public void setCurrentlyInjured(boolean currentlyInjured) {
    isCurrentlyInjured = currentlyInjured;
  }

}
