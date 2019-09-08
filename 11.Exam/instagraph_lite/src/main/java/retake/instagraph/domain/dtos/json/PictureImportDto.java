package retake.instagraph.domain.dtos.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PictureImportDto implements Serializable {

  @Expose
  private String path;

  @Expose
  private Double size;

  public PictureImportDto() {
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }
}
