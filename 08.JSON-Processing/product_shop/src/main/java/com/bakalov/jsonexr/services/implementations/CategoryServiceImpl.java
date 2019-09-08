package com.bakalov.jsonexr.services.implementations;

import com.bakalov.jsonexr.domains.dto.CategoriesByProductCountDto;
import com.bakalov.jsonexr.domains.dto.CategorySeedDto;
import com.bakalov.jsonexr.domains.entities.Category;
import com.bakalov.jsonexr.domains.entities.Product;
import com.bakalov.jsonexr.repositories.CategoryRepository;
import com.bakalov.jsonexr.services.CategoryService;
import com.bakalov.jsonexr.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(ValidatorUtil validatorUtil, ModelMapper mapper, CategoryRepository categoryRepository) {
    this.validatorUtil = validatorUtil;
    this.mapper = mapper;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void seedCategories(CategorySeedDto[] categorySeedDtos) {

    for (CategorySeedDto categorySeedDto : categorySeedDtos) {
      if (!validatorUtil.isValid(categorySeedDto)) {
        this.validatorUtil.violations(categorySeedDto)
                .forEach(v -> System.out.println(v.getMessage()));

        continue;
      }
      Category category = this.mapper.map(categorySeedDto, Category.class);

      this.categoryRepository.saveAndFlush(category);
    }
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
