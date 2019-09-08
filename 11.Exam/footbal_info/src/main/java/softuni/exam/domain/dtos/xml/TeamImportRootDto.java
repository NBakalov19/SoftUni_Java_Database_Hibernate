package softuni.exam.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamImportRootDto implements Serializable {

  @XmlElement(name = "team")
  private List<TeamImportDto> teamImportDtos;

  public TeamImportRootDto() {
    this.teamImportDtos = new ArrayList<>();
  }

  public List<TeamImportDto> getTeamImportDtos() {
    return teamImportDtos;
  }

  public void setTeamImportDtos(List<TeamImportDto> teamImportDtos) {
    this.teamImportDtos = teamImportDtos;
  }
}
