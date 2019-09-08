package app.ccb.domain.dtos.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeImportDto implements Serializable {

  @Expose
  @SerializedName("full_name")
  private String fullName;

  @Expose
  private BigDecimal salary;

  @Expose
  @SerializedName("started_on")
  private String startedOn;

  @Expose
  @SerializedName("branch_name")
  private String branchName;

  public EmployeeImportDto() {
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public String getStartedOn() {
    return startedOn;
  }

  public void setStartedOn(String startedOn) {
    this.startedOn = startedOn;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }
}
