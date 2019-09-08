package retake.instagraph.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostImportDto implements Serializable {

  @XmlElement
  private String caption;

  @XmlElement(name = "user")
  private String username;

  @XmlElement(name = "picture")
  private String picturePath;

  public PostImportDto() {
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPicture() {
    return picturePath;
  }

  public void setPicture(String picture) {
    this.picturePath = picture;
  }
}
