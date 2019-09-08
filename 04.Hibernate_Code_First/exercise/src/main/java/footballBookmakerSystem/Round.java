package footballBookmakerSystem;

import core.BaseEntityFootballDatabase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "rounds")
public class Round extends BaseEntityFootballDatabase implements Serializable {

  public Round() {
  }
}
