package softuni.exam.domain.dtos.xml.json;

import com.google.gson.annotations.Expose;
import softuni.exam.domain.entities.Position;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

public class PlayersImportDto implements Serializable {

  @Expose
  @NotNull
  private String firstName;

  @Expose
  @NotNull
  @Size(min = 3, max = 15)
  private String lastName;

  @Expose
  @NotNull
  @Min(1)
  @Max(99)
  private Integer number;

  @Expose
  @NotNull
  @Min(0)
  private BigDecimal salary;

  @Expose
  @NotNull
  private Position position;

  @Expose
  private PictureImportJson picture;

  @Expose
  private TeamImportJson team;

  public PlayersImportDto() {
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

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }


}
}
