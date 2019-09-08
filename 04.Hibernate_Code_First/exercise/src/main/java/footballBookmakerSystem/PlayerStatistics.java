package footballBookmakerSystem;


import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics implements Serializable {
  private Game game;
  private Player player;
  private short scoredGoals;
  private short playerAssists;
  private short playedMinutes;

  public PlayerStatistics() {
  }

  @Id
  @ManyToOne(targetEntity = Game.class)
  @JoinColumn(name = "game_id", referencedColumnName = "id")
  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  @Id
  @ManyToOne(targetEntity = Player.class)
  @JoinColumn(name = "player_id", referencedColumnName = "id")
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  @Column(name = "scored_goals")
  public short getScoredGoals() {
    return scoredGoals;
  }

  public void setScoredGoals(short scoredGoals) {
    this.scoredGoals = scoredGoals;
  }

  @Column(name = "player_assists")
  public short getPlayerAssists() {
    return playerAssists;
  }

  public void setPlayerAssists(short playerAssists) {
    this.playerAssists = playerAssists;
  }

  @Column(name = "played_minutes")
  public short getPlayedMinutes() {
    return playedMinutes;
  }

  public void setPlayedMinutes(short playedMinutes) {
    this.playedMinutes = playedMinutes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PlayerStatistics that = (PlayerStatistics) o;

    if (scoredGoals != that.scoredGoals) {
      return false;
    }
    if (playerAssists != that.playerAssists) {
      return false;
    }
    if (playedMinutes != that.playedMinutes) {
      return false;
    }
    if (!Objects.equals(game, that.game)) {
      return false;
    }
    return Objects.equals(player, that.player);
  }

  @Override
  public int hashCode() {
    int result = game != null ? game.hashCode() : 0;
    result = 31 * result + (player != null ? player.hashCode() : 0);
    result = 31 * result + (int) scoredGoals;
    result = 31 * result + (int) playerAssists;
    result = 31 * result + (int) playedMinutes;
    return result;
  }
}
