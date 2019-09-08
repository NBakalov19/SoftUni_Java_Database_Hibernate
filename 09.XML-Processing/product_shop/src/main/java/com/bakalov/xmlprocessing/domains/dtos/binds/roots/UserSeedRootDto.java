package com.bakalov.xmlprocessing.domains.dtos.binds.roots;

import com.bakalov.xmlprocessing.domains.dtos.binds.UserSeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedRootDto implements Serializable {

  @XmlElement(name = "user")
  private List<UserSeedDto> userSeedDtos;

  public UserSeedRootDto() {
    this.userSeedDtos = new ArrayList<>();
  }

  public List<UserSeedDto> getUserSeedDtos() {
    return userSeedDtos;
  }

  public void setUserSeedDtos(List<UserSeedDto> userSeedDtos) {
    this.userSeedDtos = userSeedDtos;
  }
}
