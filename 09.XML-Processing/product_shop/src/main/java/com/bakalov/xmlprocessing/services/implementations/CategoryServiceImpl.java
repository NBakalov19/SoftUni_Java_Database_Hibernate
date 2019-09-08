package com.bakalov.xmlprocessing.services.implementations;

import com.bakalov.xmlprocessing.domains.dtos.binds.CategorySeedDto;
import com.bakalov.xmlprocessing.domains.dtos.binds.roots.CategorySeedRootDto;
import com.bakalov.xmlprocessing.domains.dtos.views.CategoriesByProductCountDto;
import com.bakalov.xmlprocessing.domains.entities.Category;
import com.bakalov.xmlprocessing.domains.entities.Product;
import com.bakalov.xmlprocessing.repositories.CategoryRepository;
import com.bakalov.xmlprocessing.services.CategoryService;
import com.bakalov.xmlprocessing.utils.ValidatorUtil;
import com.bakalov.xmlprocessing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final CategoryRepository categoryRepository;
  private final XmlParser xmlParser;

  public CategoryServiceImpl(ValidatorUtil validatorUtil, ModelMapper mapper,
                             CategoryRepository categoryRepository, XmlParser xmlParser) {
    this.validatorUtil = validatorUtil;
    this.mapper = mapper;
    this.categoryRepository = categoryRepository;
    this.xmlParser = xmlParser;
  }

  @Override
  public void seedCategories(BufferedReader reader) {
    CategorySeedRootDto uncheckedCategorySeedRootDto = this.xmlParser.parseXml(CategorySeedRootDto.class, reader);

    CategorySeedRootDto checkedCategorySeedRootDto = new CategorySeedRootDto();

    for (CategorySeedDto categorySeedDto : uncheckedCategorySeedRootDto.getCategorySeedDtos()) {
      if (!this.validatorUtil.isValid(categorySeedDto)) {
        this.validatorUtil.violations(categorySeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }
      checkedCategorySeedRootDto.getCategorySeedDtos().add(categorySeedDto);
    }

    checkedCategorySeedRootDto.getCategorySeedDtos()
            .stream()
            .map(categorySeedDto -> this.mapper.map(categorySeedDto, Category.class))
            .forEach(this.categoryRepository::saveAndFlush);
  }


  @Override
  public List<CategoriesByProductCountDto> getCategoriesByProductCount() {

    return this.categoryRepository.findAll().stream()
            .map(category -> {
              CategoriesByProductCountDto categoriesByProductCountDto =
                      this.mapper.map(category, CategoriesByProductCountDto.class);

              categoriesByProductCountDto.setCount(category.getProducts().size());

              BigDecimal totalRevenue =
                      category.getProducts()
                              .stream()
                              .map(Product::getPrice)
                              .reduce(BigDecimal::add)
                              .orElse(BigDecimal.ZERO);

              categoriesByProductCountDto.setTotalRevenue(totalRevenue);

              if (totalRevenue.equals(BigDecimal.ZERO)) {
                categoriesByProductCountDto.setAveragePrice(0);
              } else {
                categoriesByProductCountDto
                        .setAveragePrice(totalRevenue.doubleValue() / categoriesByProductCountDto.getCount());
              }
              return categoriesByProductCountDto;
            }).sorted(Comparator.comparing(CategoriesByProductCountDto::getCount).reversed())
            .collect(Collectors.toList());
  }
}
