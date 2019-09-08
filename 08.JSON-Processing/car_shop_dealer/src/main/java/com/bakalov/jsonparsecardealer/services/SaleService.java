package com.bakalov.jsonparsecardealer.services;

import com.bakalov.jsonparsecardealer.domains.dtos.view.SalesDiscountsDto;

import java.util.List;

public interface SaleService {

  void seedSales();

  List<SalesDiscountsDto> getSalesDetails();
}
