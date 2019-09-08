package footballBookmakerSystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "positions")
public class Position implements Serializable {

  private String id;
  private String positionDescription;

  public Position() {
  }

  @Id
  @Column(nullable = false, unique = true, length = 2)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column(name = "description")
  public String getPositionDescription() {
    return positionDescription;
  }

  public void setPositionDescription(String positionDescription) {
    this.positionDescription = positionDescription;
  }
}
