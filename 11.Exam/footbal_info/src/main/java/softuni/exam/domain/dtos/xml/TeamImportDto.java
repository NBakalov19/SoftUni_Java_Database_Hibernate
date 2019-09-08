package softuni.exam.domain.dtos.xml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamImportDto implements Serializable {

  @XmlElement
  @NotNull
  @Size(min = 3, max = 20)
  private String name;

  @XmlElement(name = "picture")
  @NotNull
  private PictureImportDto pictureImportDto;

  public TeamImportDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PictureImportDto getPictureImportDto() {
    return pictureImportDto;
  }

  public void setPictureImportDto(PictureImportDto pictureImportDto) {
    this.pictureImportDto = pictureImportDto;
  }
}
