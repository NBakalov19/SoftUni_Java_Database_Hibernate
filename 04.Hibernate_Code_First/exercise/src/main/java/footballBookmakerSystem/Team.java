package footballBookmakerSystem;

import core.BaseEntityFootballDatabase;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "teams")
public class Team extends BaseEntityFootballDatabase implements Serializable {

  private byte[] logo;
  private String initials;
  private Color primaryKit;
  private Color secondaryKit;
  private Town town;
  private BigDecimal budget;

  public Team() {
  }

  @Column(name = "logo")
  public byte[] getLogo() {
    return logo;
  }

  public void setLogo(byte[] logo) {
    this.logo = logo;
  }

  @Column(name = "initials", length = 3)
  public String getInitials() {
    return initials;
  }

  public void setInitials(String initials) {
    this.initials = initials;
  }

  @ManyToOne(targetEntity = Color.class)
  @JoinColumn(name = "primary_kit_color", referencedColumnName = "id")
  public Color getPrimaryKit() {
    return primaryKit;
  }

  public void setPrimaryKit(Color primaryKit) {
    this.primaryKit = primaryKit;
  }

  @ManyToOne(targetEntity = Color.class)
  @JoinColumn(name = "secondary_kit_color", referencedColumnName = "id")
  public Color getSecondaryKit() {
    return secondaryKit;
  }

  public void setSecondaryKit(Color secondaryKit) {
    this.secondaryKit = secondaryKit;
  }

  @ManyToOne(targetEntity = Town.class)
  @JoinColumn(name = "town", referencedColumnName = "id")
  public Town getTown() {
    return town;
  }

  public void setTown(Town town) {
    this.town = town;
  }

  @Column(name = "budget", nullable = false)
  public BigDecimal getBudget() {
    return budget;
  }

  public void setBudget(BigDecimal budget) {
    this.budget = budget;
  }
}
