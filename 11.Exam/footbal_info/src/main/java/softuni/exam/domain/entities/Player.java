package softuni.exam.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {

  @Column(name = "first_name")
  @NotNull
  private String firstName;

  @Column(name = "last_name")
  @NotNull
  @Size(min = 3, max = 15)
  private String lastName;

  @Column
  @NotNull
  @Min(1)
  @Max(99)
  private Integer number;

  @Column
  @NotNull
  @Min(0)
  private BigDecimal salary;

  @Column
  @NotNull
  @Enumerated(EnumType.STRING)
  private Position position;

  @NotNull
  @ManyToOne(targetEntity = Picture.class)
  @JoinColumn(name = "picture_id", referencedColumnName = "id")
  private Picture picture;

  @NotNull
  @ManyToOne(targetEntity = Team.class)
  @JoinColumn(name = "team_id", referencedColumnName = "id")
  private Team team;

  public Player() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Picture getPicture() {
    return picture;
  }

  public void setPicture(Picture picture) {
    this.picture = picture;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }
}
