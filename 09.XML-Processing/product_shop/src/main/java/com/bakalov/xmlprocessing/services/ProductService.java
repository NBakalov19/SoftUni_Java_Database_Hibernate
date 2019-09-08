package com.bakalov.xmlprocessing.services;

import com.bakalov.xmlprocessing.domains.dtos.views.ProductInRangeDto;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

  void seedProducts(BufferedReader reader);

  List<ProductInRangeDto> getProductsInRange(BigDecimal more, BigDecimal less);
}
