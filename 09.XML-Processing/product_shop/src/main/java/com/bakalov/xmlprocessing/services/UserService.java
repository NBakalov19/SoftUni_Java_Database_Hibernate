package com.bakalov.xmlprocessing.services;

import com.bakalov.xmlprocessing.domains.dtos.views.SuccessfullySellersDto;
import com.bakalov.xmlprocessing.domains.dtos.views.UsersWithSalesListDto;

import java.io.BufferedReader;
import java.util.List;

public interface UserService {
  void seedUsers(BufferedReader reader);

  List<SuccessfullySellersDto> getSuccessfulSellers();

  UsersWithSalesListDto getSellsByUser();
}
