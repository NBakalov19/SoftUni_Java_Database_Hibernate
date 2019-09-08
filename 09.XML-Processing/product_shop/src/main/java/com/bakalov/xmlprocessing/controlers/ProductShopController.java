package com.bakalov.xmlprocessing.controlers;

import com.bakalov.xmlprocessing.domains.dtos.views.CategoriesByProductCountDto;
import com.bakalov.xmlprocessing.domains.dtos.views.ProductInRangeDto;
import com.bakalov.xmlprocessing.domains.dtos.views.SuccessfullySellersDto;
import com.bakalov.xmlprocessing.domains.dtos.views.UsersWithSalesListDto;
import com.bakalov.xmlprocessing.domains.dtos.views.roots.CategoriesByProductCountRootDto;
import com.bakalov.xmlprocessing.domains.dtos.views.roots.ProductInRangeRootDto;
import com.bakalov.xmlprocessing.domains.dtos.views.roots.SuccessfullySellersRootDto;
import com.bakalov.xmlprocessing.services.CategoryService;
import com.bakalov.xmlprocessing.services.ProductService;
import com.bakalov.xmlprocessing.services.UserService;
import com.bakalov.xmlprocessing.utils.FileIoUtil;
import com.bakalov.xmlprocessing.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.bakalov.xmlprocessing.utils.Constants.*;


@Controller
public class ProductShopController implements CommandLineRunner {

  private final FileIoUtil fileUtil;
  private final UserService userService;
  private final CategoryService categoryService;
  private final ProductService productService;
  private final XmlParser xmlParser;

  @Autowired
  public ProductShopController(FileIoUtil fileUtil,
                               UserService userService,
                               CategoryService categoryService,
                               ProductService productService, XmlParser xmlParser) {
    this.fileUtil = fileUtil;
    this.userService = userService;
    this.categoryService = categoryService;
    this.productService = productService;
    this.xmlParser = xmlParser;
  }

  @Override
  public void run(String... args) throws Exception {


    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Please enter \"seed\" to seed into database or "
            + "number from to 1 to 4 for execute queries:");
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
          System.out.println("Valid commands:\n"
                  + "\"seed\" -> seed database\n"
                  + "Number from 1 to 4 -> execute query\n"
                  + "\"stop\" -> stop application");
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
    this.userService.seedUsers(
            this.fileUtil.readFileContent(USER_INPUT_XML_FILE_PATH));
  }

  private void seedCategories() throws IOException {

    this.categoryService.seedCategories(
            this.fileUtil.readFileContent(CATEGORY_INPUT_XML_FILE_PATH));
  }

  private void seedProducts() throws IOException {
    this.productService.seedProducts(
            this.fileUtil.readFileContent(PRODUCT_INPUT_XML_FILE_PATH));
  }


  private void productsInRange() throws IOException {
    List<ProductInRangeDto> productInRangeDtos =
            this.productService.getProductsInRange(MORE, LESS);

    ProductInRangeRootDto products = new ProductInRangeRootDto();
    products.setProducts(productInRangeDtos);

    this.xmlParser.objectToXmlFile(products,
            this.fileUtil.writeFileContent(PRODUCT_IN_RANGE_FILE_PATH));
  }

  private void getSuccessfullySellers() throws IOException {
    List<SuccessfullySellersDto> sellersDtos = this.userService.getSuccessfulSellers();

    SuccessfullySellersRootDto sellers = new SuccessfullySellersRootDto();
    sellers.setSellersDtos(sellersDtos);

    this.xmlParser.objectToXmlFile(sellers,
            this.fileUtil.writeFileContent(SUCCESSFULLY_SELLERS_FILE_PATH));
  }

  private void getCategoriesByProductCount() throws IOException {
    List<CategoriesByProductCountDto> categoriesByProductCountDtos =
            this.categoryService.getCategoriesByProductCount();

    CategoriesByProductCountRootDto categories = new CategoriesByProductCountRootDto();
    categories.setCategories(categoriesByProductCountDtos);

    this.xmlParser.objectToXmlFile(categories,
            this.fileUtil.writeFileContent(CATEGORIES_BY_PRODUCTS));
  }

  private void getUsersAndProducts() throws IOException {
    UsersWithSalesListDto usersWithSalesListDto = this.userService.getSellsByUser();

    this.xmlParser.objectToXmlFile(usersWithSalesListDto,
            this.fileUtil.writeFileContent(USERS_AND_PRODUCTS_FILE_PATH));
  }
}