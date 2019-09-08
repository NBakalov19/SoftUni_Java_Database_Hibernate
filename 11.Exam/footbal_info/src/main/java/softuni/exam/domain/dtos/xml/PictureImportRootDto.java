package softuni.exam.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureImportRootDto implements Serializable {

  @XmlElement(name = "picture")
  private List<PictureImportDto> importDtos;

  public PictureImportRootDto() {
    this.importDtos = new ArrayList<>();
  }

  public List<PictureImportDto> getImportDtos() {
    return importDtos;
  }

  public void setImportDtos(List<PictureImportDto> importDtos) {
    this.importDtos = importDtos;
  }
}
