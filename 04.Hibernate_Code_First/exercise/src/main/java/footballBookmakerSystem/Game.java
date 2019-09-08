package footballBookmakerSystem;

import core.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "games")
public class Game extends BaseEntity implements Serializable {

  private Team homeTeam;
  private Team awayTeam;
  private byte homeGoals;
  private byte awayGoals;
  private LocalDateTime dateTime;
  private double homeTeamWinBetRate;
  private double awayTeamWinBetRate;
  private double drawGameBetRate;
  private Round round;
  private Competition competition;

  public Game() {
  }

  @OneToOne(targetEntity = Team.class)
  @JoinColumn(name = "home_team", referencedColumnName = "id")
  public Team getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(Team homeTeam) {
    this.homeTeam = homeTeam;
  }

  @OneToOne(targetEntity = Team.class)
  @JoinColumn(name = "away_team", referencedColumnName = "id")
  public Team getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(Team awayTeam) {
    this.awayTeam = awayTeam;
  }

  @Column(name = "home_team_goals")
  public byte getHomeGoals() {
    return homeGoals;
  }

  public void setHomeGoals(byte homeGoals) {
    this.homeGoals = homeGoals;
  }

  @Column(name = "away_team_goals")
  public byte getAwayGoals() {
    return awayGoals;
  }

  public void setAwayGoals(byte awayGoals) {
    this.awayGoals = awayGoals;
  }

  @Column(name = "date_tiem")
  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  @Column(name = "home_team_win_bet_rate")
  public double getHomeTeamWinBetRate() {
    return homeTeamWinBetRate;
  }

  public void setHomeTeamWinBetRate(double homeTeamWinBetRate) {
    this.homeTeamWinBetRate = homeTeamWinBetRate;
  }

  @Column(name = "away_team_win_bet_rate")
  public double getAwayTeamWinBetRate() {
    return awayTeamWinBetRate;
  }

  public void setAwayTeamWinBetRate(double awayTeamWinBetRate) {
    this.awayTeamWinBetRate = awayTeamWinBetRate;
  }

  @Column(name = "draw_game_bet_rate")
  public double getDrawGameBetRate() {
    return drawGameBetRate;
  }

  public void setDrawGameBetRate(double drawGameBetRate) {
    this.drawGameBetRate = drawGameBetRate;
  }

  @ManyToOne(targetEntity = Round.class)
  @JoinColumn(name = "round_id", referencedColumnName = "id")
  public Round getRound() {
    return round;
  }

  public void setRound(Round round) {
    this.round = round;
  }

  @ManyToOne(targetEntity = Competition.class)
  @JoinColumn(name = "competiton_id", referencedColumnName = "id")
  public Competition getCompetition() {
    return competition;
  }

  public void setCompetition(Competition competition) {
    this.competition = competition;
  }
}
