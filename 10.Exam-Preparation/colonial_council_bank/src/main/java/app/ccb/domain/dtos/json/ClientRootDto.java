package app.ccb.domain.dtos.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClientRootDto implements Serializable {

  @Expose
  @SerializedName("first_name")
  private String firstName;

  @Expose
  @SerializedName("last_name")
  private String lastName;

  @Expose
  private Integer age;

  @Expose
  @SerializedName("appointed_employee")
  private String employeeName;

  public ClientRootDto() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }
}
