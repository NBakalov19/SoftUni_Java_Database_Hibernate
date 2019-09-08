package com.bakalov.xmlprocessing.services;

import com.bakalov.xmlprocessing.domains.dtos.views.CategoriesByProductCountDto;

import java.io.BufferedReader;
import java.util.List;

public interface CategoryService {

  void seedCategories(BufferedReader reader);

  List<CategoriesByProductCountDto> getCategoriesByProductCount();
}
