package com.bakalov.jsonexr.services;

import com.bakalov.jsonexr.domains.dto.ProductInRangeDto;
import com.bakalov.jsonexr.domains.dto.ProductSeedDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

  void seedProducts(ProductSeedDto[] productSeedDtos);

  List<ProductInRangeDto> getProductsInRange(BigDecimal more, BigDecimal less);
}
