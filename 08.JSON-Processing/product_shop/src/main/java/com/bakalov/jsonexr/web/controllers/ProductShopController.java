package com.bakalov.jsonexr.web.controllers;

import com.bakalov.jsonexr.domains.dto.*;
import com.bakalov.jsonexr.services.CategoryService;
import com.bakalov.jsonexr.services.ProductService;
import com.bakalov.jsonexr.services.UserService;
import com.bakalov.jsonexr.utils.Constants;
import com.bakalov.jsonexr.utils.FileIoUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.bakalov.jsonexr.utils.Constants.PRODUCT_IN_RANGE_FILE_PATH;

@Controller
public class ProductShopController implements CommandLineRunner {

  private final Gson gson;
  private final FileIoUtil fileUtil;
  private final UserService userService;
  private final CategoryService categoryService;
  private final ProductService productService;

  @Autowired
  public ProductShopController(Gson gson,
                               FileIoUtil fileUtil,
                               UserService userService,
                               CategoryService categoryService,
                               ProductService productService) {
    this.gson = gson;
    this.fileUtil = fileUtil;
    this.userService = userService;
    this.categoryService = categoryService;
    this.productService = productService;
  }

  @Override
  public void run(String... args) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    System.out.println("Please enter \"seed\" to seed into database or  number from to 1 to 4 for execute queries:");
    while (true) {
      String command = reader.readLine().toLowerCase();

      switch (command) {

        case "seed":
          this.seedDatabase();
          break;
        case "1":
          this.productsInRange();
          break;
        case "2":
          this.getSuccessfullySellers();
          break;
        case "3":
          this.getCategoriesByProductCount();
          break;
        case "4":
          this.getUsersAndProducts();
          break;
        case "stop":
          System.exit(0);
          break;
        default:
          System.out.println("Valid commands:\n" +
                  "\"seed\" -> seed database\n" +
                  "Number from 1 to 4 -> execute query\n" +
                  "\"stop\" -> stop application");
          break;
      }
    }
  }


  private void seedDatabase() throws IOException {
    this.seedUsers();
    this.seedCategories();
    this.seedProducts();
  }

  private void seedUsers() throws IOException {
    String content = this.fileUtil.readFileContent(Constants.USER_JSON_FILE_PATH);

    UserSeedDto[] userSeedDtos = this.gson.fromJson(content, UserSeedDto[].class);

    this.userService.seedUser(userSeedDtos);
  }

  private void seedCategories() throws IOException {
    String content = this.fileUtil.readFileContent(Constants.CATEGORY_JSON_FILE_PATH);

    CategorySeedDto[] categorySeedDtos = this.gson.fromJson(content, CategorySeedDto[].class);

    this.categoryService.seedCategories(categorySeedDtos);
  }

  private void seedProducts() throws IOException {
    String content = this.fileUtil.readFileContent(Constants.PRODUCT_JSON_FILE_PATH);

    ProductSeedDto[] productSeedDtos = this.gson.fromJson(content, ProductSeedDto[].class);

    this.productService.seedProducts(productSeedDtos);
  }


  private void productsInRange() throws IOException {
    List<ProductInRangeDto> productsInRangeDtos = this.productService.
            getProductsInRange(Constants.MORE, Constants.LESS);

    String productsInRange = this.gson.toJson(productsInRangeDtos);

    this.fileUtil.writeFileContent(productsInRange, PRODUCT_IN_RANGE_FILE_PATH);
  }

  private void getSuccessfullySellers() throws IOException {
    List<SuccessfullySellersDto> successfullySellersDtos = this.userService.getSuccessfulSellers();

    String successfullySellers = this.gson.toJson(successfullySellersDtos);

    this.fileUtil.writeFileContent(successfullySellers, Constants.SUCCESSFULLY_SELLERS_FILE_PATH);
  }

  private void getCategoriesByProductCount() throws IOException {
    List<CategoriesByProductCountDto> categoriesByProductCountDtos = this.categoryService.getCategoriesByProductCount();

    String categories = this.gson.toJson(categoriesByProductCountDtos);

    this.fileUtil.writeFileContent(categories, Constants.CATEGORIES_BY_PRODUCTS);
  }

  private void getUsersAndProducts() throws IOException {
    UsersWithSalesListDto usersWithSalesListDto = this.userService.getSellsByUser();

    String users = this.gson.toJson(usersWithSalesListDto);

    this.fileUtil.writeFileContent(users, Constants.USERS_AND_PRODUCTS_FILE_PATH);
  }
}
