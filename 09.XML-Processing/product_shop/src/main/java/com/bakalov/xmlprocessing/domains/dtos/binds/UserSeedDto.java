package com.bakalov.xmlprocessing.domains.dtos.binds;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedDto implements Serializable {

  @XmlAttribute(name = "first-name")
  private String firstName;

  @XmlAttribute(name = "last-name")
  @NotNull(message = "Last name can`t be null")
  @Size(min = 3, message = "Last name should at least 3 symbols")
  private String lastName;

  @XmlAttribute(name = "age")
  @NotNull(message = "Age can`t be null")
  @Min(value = 0, message = "Age can`t be negative number")
  private Integer age;

  public UserSeedDto() {
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
}
