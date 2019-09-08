package softuni.exam.domain.dtos.xml.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class TeamImportJson implements Serializable {

  @Expose
  @NotNull
  @Size(min = 3, max = 20)
  private String name;

  @Expose
  @NotNull
  private PictureImportJson picture;

  public TeamImportJson() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PictureImportJson getPicture() {
    return picture;
  }

  public void setPicture(PictureImportJson picture) {
    this.picture = picture;
  }
}
