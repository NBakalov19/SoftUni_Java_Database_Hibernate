package retake.instagraph.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostImportRootDto implements Serializable {

  @XmlElement(name = "post")
  private List<PostImportDto> postImportDtos;

  public PostImportRootDto() {
    this.postImportDtos = new ArrayList<>();
  }

  public List<PostImportDto> getPostImportDtos() {
    return postImportDtos;
  }

  public void setPostImportDtos(List<PostImportDto> postImportDtos) {
    this.postImportDtos = postImportDtos;
  }
}
