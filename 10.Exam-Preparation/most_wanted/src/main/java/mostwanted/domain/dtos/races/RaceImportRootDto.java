package mostwanted.domain.dtos.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;


@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportRootDto {

  @XmlElement(name = "race")
  private List<RaceImportDto> raceList;

  public List<RaceImportDto> getRaceList() {
    return Collections.unmodifiableList(raceList);
  }

  public void setRaceList(List<RaceImportDto> raceList) {
    this.raceList = raceList;
  }
}
