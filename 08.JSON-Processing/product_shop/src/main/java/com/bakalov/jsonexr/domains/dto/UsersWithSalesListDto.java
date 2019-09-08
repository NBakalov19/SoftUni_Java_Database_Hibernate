package com.bakalov.jsonexr.domains.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsersWithSalesListDto implements Serializable {

  @Expose
  private Integer usersCount;
  @Expose
  private List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users;

  public UsersWithSalesListDto() {
    users = new ArrayList<>();
  }

  public Integer getUsersCount() {
    return usersCount;
  }

  public void setUsersCount(Integer usersCount) {
    this.usersCount = usersCount;
  }

  public List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> getUsers() {
    return users;
  }

  public void setUsers(List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users) {
    this.users = users;
  }
}
