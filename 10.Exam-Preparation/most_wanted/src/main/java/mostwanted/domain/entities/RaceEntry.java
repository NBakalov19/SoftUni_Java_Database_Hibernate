package mostwanted.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "race_entries")
public class RaceEntry extends BaseEntity {

  @Column(name = "has_finished")
  private Boolean hasFinished;

  @Column(name = "finish_time")
  private Double finishTime;

  @ManyToOne(targetEntity = Car.class)
  @JoinColumn(name = "car_id", referencedColumnName = "id")
  private Car car;

  @ManyToOne(targetEntity = Race.class)
  @JoinColumn(name = "race_id", referencedColumnName = "id")
  private Race race;

  @ManyToOne(targetEntity = Racer.class)
  @JoinColumn(name = "racer_id", referencedColumnName = "id")
  private Racer racer;

  public RaceEntry() {
  }

  public Boolean getHasFinished() {
    return hasFinished;
  }

  public void setHasFinished(Boolean hasFinished) {
    this.hasFinished = hasFinished;
  }

  public Double getFinishTime() {
    return finishTime;
  }

  public void setFinishTime(Double finishTime) {
    this.finishTime = finishTime;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Race getRace() {
    return race;
  }

  public void setRace(Race race) {
    this.race = race;
  }

  public Racer getRacer() {
    return racer;
  }

  public void setRacer(Racer racer) {
    this.racer = racer;
  }
}
