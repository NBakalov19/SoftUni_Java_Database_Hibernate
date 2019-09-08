package footballBookmakerSystem;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bet_games")
public class BetGame implements Serializable {
  private Game game;
  private Bet bet;
  private ResultPrediction resultPrediction;

  public BetGame() {
  }

  @Id
  @OneToOne
  @JoinColumn(name = "game_id", referencedColumnName = "id")
  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  @OneToOne(targetEntity = Bet.class)
  @JoinColumn(name = "bet_id", referencedColumnName = "id")
  public Bet getBet() {
    return bet;
  }

  public void setBet(Bet bet) {
    this.bet = bet;
  }

  @OneToOne(targetEntity = ResultPrediction.class)
  @JoinColumn(name = "result_prediction", referencedColumnName = "id")
  public ResultPrediction getResultPrediction() {
    return resultPrediction;
  }

  public void setResultPrediction(ResultPrediction resultPrediction) {
    this.resultPrediction = resultPrediction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BetGame betGame = (BetGame) o;

    if (!Objects.equals(game, betGame.game)) {
      return false;
    }
    if (!Objects.equals(bet, betGame.bet)) {
      return false;
    }
    return Objects.equals(resultPrediction, betGame.resultPrediction);
  }

  @Override
  public int hashCode() {
    int result = game != null ? game.hashCode() : 0;
    result = 31 * result + (bet != null ? bet.hashCode() : 0);
    result = 31 * result + (resultPrediction != null ? resultPrediction.hashCode() : 0);
    return result;
  }
}
