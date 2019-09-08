package mostwanted.domain.dtos.raceentries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportRootDto implements Serializable {

  @XmlElement(name = "race-entry")
  private List<RaceEntryImportDto> raceEntryImportDtos;

  public RaceEntryImportRootDto() {
    this.raceEntryImportDtos = new ArrayList<>();
  }

  public List<RaceEntryImportDto> getRaceEntryImportDtos() {
    return raceEntryImportDtos;
  }

  public void setRaceEntryImportDtos(List<RaceEntryImportDto> raceEntryImportDtos) {
    this.raceEntryImportDtos = raceEntryImportDtos;
  }
}