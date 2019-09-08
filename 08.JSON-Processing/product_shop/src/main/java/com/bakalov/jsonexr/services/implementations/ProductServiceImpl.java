package com.bakalov.jsonexr.services.implementations;

import com.bakalov.jsonexr.domains.dto.ProductInRangeDto;
import com.bakalov.jsonexr.domains.dto.ProductSeedDto;
import com.bakalov.jsonexr.domains.entities.Category;
import com.bakalov.jsonexr.domains.entities.Product;
import com.bakalov.jsonexr.domains.entities.User;
import com.bakalov.jsonexr.repositories.CategoryRepository;
import com.bakalov.jsonexr.repositories.ProductRepository;
import com.bakalov.jsonexr.repositories.UserRepository;
import com.bakalov.jsonexr.services.ProductService;
import com.bakalov.jsonexr.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;
  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository,
                            CategoryRepository categoryRepository, ValidatorUtil validatorUtil,
                            ModelMapper modelMapper) {

    this.productRepository = productRepository;
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;

    this.validatorUtil = validatorUtil;
    this.mapper = modelMapper;
  }

  @Override
  public void seedProducts(ProductSeedDto[] productSeedDtos) {

    for (ProductSeedDto productSeedDto : productSeedDtos) {
      if (!validatorUtil.isValid(productSeedDto)) {
        this.validatorUtil.violations(productSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));

        continue;
      }

      Product product = this.mapper.map(productSeedDto, Product.class);
      product.setSeller(this.getRandomSeller());
      product.setBuyer(this.getRandomBuyer());
      product.setCategories(this.getRandomCategories());

      this.productRepository.saveAndFlush(product);
    }
  }

  private User getRandomSeller() {
    Random random = new Random();

    Integer id = random.nextInt((int) this.userRepository.count() - 1) + 1;

    return this.userRepository.getOne(id);
  }

  private User getRandomBuyer() {
    Random random = new Random();

    Integer id = random.nextInt((int) this.userRepository.count() - 1) + 1;

    if (id % 7 == 0) {
      return null;
    }

    return this.userRepository.getOne(id);
  }


  private Category getRandomCategory() {
    Random random = new Random();

    Integer id = random.nextInt((int) this.categoryRepository.count() - 1) + 1;

    return this.categoryRepository.getOne(id);
  }

  private List<Category> getRandomCategories() {
    List<Category> categories = new ArrayList<>();

    Random random = new Random();

    int size = random.nextInt((int) this.categoryRepository.count() - 1) + 1;

    for (int i = 0; i < size; i++) {
      categories.add(this.getRandomCategory());
    }

    return categories;
  }

  @Override
  public List<ProductInRangeDto> getProductsInRange(BigDecimal more, BigDecimal less) {
    List<Product> productEntities = this.productRepository
            .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(more, less);

    List<ProductInRangeDto> productInRangeDtos = new ArrayList<>();

    for (Product productEntity : productEntities) {
      ProductInRangeDto productInRangeDto = this.mapper.map(productEntity, ProductInRangeDto.class);
      productInRangeDto.setSeller(String.format("%s %s",
              productEntity.getSeller().getFirstName(),
              productEntity.getSeller().getLastName()));

      productInRangeDtos.add(productInRangeDto);
    }

    return productInRangeDtos;
  }
}