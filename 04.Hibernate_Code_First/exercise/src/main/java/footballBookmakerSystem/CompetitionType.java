package footballBookmakerSystem;

import core.BaseEntityFootballDatabase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "competition_types")
public class CompetitionType extends BaseEntityFootballDatabase implements Serializable {

  public CompetitionType() {
  }
}
