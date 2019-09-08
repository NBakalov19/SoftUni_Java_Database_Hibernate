package com.bakalov.jsonexr.services;

import com.bakalov.jsonexr.domains.dto.SuccessfullySellersDto;
import com.bakalov.jsonexr.domains.dto.UserSeedDto;
import com.bakalov.jsonexr.domains.dto.UsersWithSalesListDto;

import java.util.List;

public interface UserService {
  void seedUser(UserSeedDto[] userSeedDtos);

  List<SuccessfullySellersDto> getSuccessfulSellers();

  UsersWithSalesListDto getSellsByUser();
}
