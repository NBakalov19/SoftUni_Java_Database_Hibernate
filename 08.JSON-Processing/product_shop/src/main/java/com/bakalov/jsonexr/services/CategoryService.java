package com.bakalov.jsonexr.services;

import com.bakalov.jsonexr.domains.dto.CategoriesByProductCountDto;
import com.bakalov.jsonexr.domains.dto.CategorySeedDto;

import java.util.List;

public interface CategoryService {

  void seedCategories(CategorySeedDto[] categorySeedDtos);

  List<CategoriesByProductCountDto> getCategoriesByProductCount();
}
