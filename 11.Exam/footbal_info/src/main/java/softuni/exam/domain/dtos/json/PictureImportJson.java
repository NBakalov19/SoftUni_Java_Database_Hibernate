package softuni.exam.domain.dtos.xml.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PictureImportJson implements Serializable {

  @Expose
  @NotNull
  private String url;

  public PictureImportJson() {
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
