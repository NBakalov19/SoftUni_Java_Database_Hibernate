package com.bakalov.xmlproccesing.services;

import com.bakalov.xmlproccesing.domains.dtos.views.SalesDiscountsDto;

import java.util.List;

public interface SaleService {

  void seedCustomers();

  List<SalesDiscountsDto> getSalesDetails();
}
