package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportDto implements Serializable {

  @XmlAttribute
  private Integer id;

  public EntryImportDto() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
