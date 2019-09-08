package app.ccb.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BaseEntity {

  @Column(name = "account_number", nullable = false)
  private String accountNumber;

  @Column
  private BigDecimal balance;

  @OneToOne(targetEntity = Client.class)
  @JoinColumn(name = "client_id", referencedColumnName = "id")
  private Client client;

  @OneToMany(targetEntity = Card.class, mappedBy = "bankAccount")
  private Set<Card> cards;

  public BankAccount() {
    this.cards = new HashSet<>();
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Set<Card> getCards() {
    return cards;
  }

  public void setCards(Set<Card> cards) {
    this.cards = cards;
  }
}
