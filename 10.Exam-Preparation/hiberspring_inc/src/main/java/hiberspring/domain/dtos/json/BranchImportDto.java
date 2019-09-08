package hiberspring.domain.dtos.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class BranchImportDto implements Serializable {

  @Expose
  private String name;

  @Expose
  private String town;

  public BranchImportDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }
}
