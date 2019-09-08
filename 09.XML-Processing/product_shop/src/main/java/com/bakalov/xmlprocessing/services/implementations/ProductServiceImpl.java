package com.bakalov.xmlprocessing.services.implementations;

import com.bakalov.xmlprocessing.domains.dtos.binds.ProductSeedDto;
import com.bakalov.xmlprocessing.domains.dtos.binds.roots.ProductSeedRootDto;
import com.bakalov.xmlprocessing.domains.dtos.views.ProductInRangeDto;
import com.bakalov.xmlprocessing.domains.entities.Category;
import com.bakalov.xmlprocessing.domains.entities.Product;
import com.bakalov.xmlprocessing.domains.entities.User;
import com.bakalov.xmlprocessing.repositories.CategoryRepository;
import com.bakalov.xmlprocessing.repositories.ProductRepository;
import com.bakalov.xmlprocessing.repositories.UserRepository;
import com.bakalov.xmlprocessing.services.ProductService;
import com.bakalov.xmlprocessing.utils.ValidatorUtil;
import com.bakalov.xmlprocessing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
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
  private final XmlParser xmlParser;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository,
                            CategoryRepository categoryRepository, ValidatorUtil validatorUtil,
                            ModelMapper modelMapper, XmlParser xmlParser) {

    this.productRepository = productRepository;
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
    this.validatorUtil = validatorUtil;
    this.mapper = modelMapper;
    this.xmlParser = xmlParser;
  }

  @Override
  public void seedProducts(BufferedReader reader) {
    ProductSeedRootDto uncheckedProductSeedRootDto = this.xmlParser.parseXml(ProductSeedRootDto.class, reader);

    ProductSeedRootDto checkedProductSeedRootDto = new ProductSeedRootDto();

    for (ProductSeedDto productSeedDto : uncheckedProductSeedRootDto.getProductSeedDtos()) {
      if (!this.validatorUtil.isValid(productSeedDto)) {
        this.validatorUtil.violations(productSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }
      checkedProductSeedRootDto.getProductSeedDtos().add(productSeedDto);
    }

    checkedProductSeedRootDto.getProductSeedDtos()
            .stream()
            .map(productSeedDto -> {
              Product product = this.mapper.map(productSeedDto, Product.class);
              product.setSeller(this.getRandomSeller());
              product.setBuyer(this.getRandomBuyer());
              product.setCategories(this.getRandomCategories());

              return product;
            })
            .forEach(this.productRepository::saveAndFlush);
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
    List<Product> products = this.productRepository
            .findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(more, less);

    List<ProductInRangeDto> productInRangeDtos = new ArrayList<>();

    for (Product product : products) {
      ProductInRangeDto productInRangeDto = this.mapper.map(product, ProductInRangeDto.class);
      productInRangeDto.setSeller(String.format("%s %s",
              product.getSeller().getFirstName(),
              product.getSeller().getLastName()));

      productInRangeDtos.add(productInRangeDto);
    }

    return productInRangeDtos;
  }
}