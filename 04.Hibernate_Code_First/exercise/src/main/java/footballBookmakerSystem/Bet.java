package footballBookmakerSystem;

import core.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bets")
public class Bet extends BaseEntity implements Serializable {

  private BigDecimal betMoney;
  private LocalDateTime dateTimeOfBet;
  private User user;

  public Bet() {
  }

  @Column(name = "bet_money")
  public BigDecimal getBetMoney() {
    return betMoney;
  }

  public void setBetMoney(BigDecimal betMoney) {
    this.betMoney = betMoney;
  }

  @Column(name = "date_time")
  public LocalDateTime getDateTimeOfBet() {
    return dateTimeOfBet;
  }

  public void setDateTimeOfBet(LocalDateTime dateTimeOfBet) {
    this.dateTimeOfBet = dateTimeOfBet;
  }

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
