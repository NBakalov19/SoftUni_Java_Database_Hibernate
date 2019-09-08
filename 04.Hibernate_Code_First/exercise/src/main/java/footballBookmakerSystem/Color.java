package footballBookmakerSystem;

import core.BaseEntityFootballDatabase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "colors")
public class Color extends BaseEntityFootballDatabase implements Serializable {

  public Color() {
  }
}
