package footballBookmakerSystem;

import core.BaseEntityFootballDatabase;

import java.io.Serializable;

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntityFootballDatabase implements Serializable {

  private CompetitionType competitionType;

  public Competition() {
  }

  @ManyToOne(targetEntity = CompetitionType.class)
  @JoinColumn(name = "competition_type", referencedColumnName = "id")
  public CompetitionType getCompetitionType() {
    return competitionType;
  }

  public void setCompetitionType(CompetitionType competitionType) {
    this.competitionType = competitionType;
  }
}
