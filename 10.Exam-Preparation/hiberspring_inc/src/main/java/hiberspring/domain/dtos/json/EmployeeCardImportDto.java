package hiberspring.domain.dtos.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class EmployeeCardImportDto implements Serializable {

  @Expose
  private String number;

  public EmployeeCardImportDto() {
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }
}
