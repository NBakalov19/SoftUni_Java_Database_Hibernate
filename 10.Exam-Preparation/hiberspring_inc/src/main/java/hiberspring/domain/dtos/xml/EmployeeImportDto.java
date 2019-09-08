package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeImportDto implements Serializable {

  @XmlAttribute(name = "first-name")
  private String firstName;

  @XmlAttribute(name = "last-name")
  private String lastName;

  @XmlAttribute
  private String position;

  @XmlElement(name = "card")
  private String cardNumber;

  @XmlElement(name = "branch")
  private String branchName;

  public EmployeeImportDto() {
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

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }
}
