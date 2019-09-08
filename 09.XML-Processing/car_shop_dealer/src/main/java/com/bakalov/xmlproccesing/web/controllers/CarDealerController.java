package com.bakalov.xmlproccesing.web.controllers;

import com.bakalov.xmlproccesing.domains.dtos.views.*;
import com.bakalov.xmlproccesing.domains.dtos.views.roots.*;
import com.bakalov.xmlproccesing.services.*;
import com.bakalov.xmlproccesing.utils.FileIoUtil;
import com.bakalov.xmlproccesing.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.bakalov.xmlproccesing.utils.Constants.*;


@Controller
public class CarDealerController implements CommandLineRunner {

  private final FileIoUtil fileIoUtil;
  private final SupplierService supplierService;
  private final PartService partService;
  private final CarService carService;
  private final CustomerService customerService;
  private final SaleService saleService;
  private final XmlParser xmlParser;

  @Autowired
  public CarDealerController(FileIoUtil fileIoUtil, SupplierService supplierService,
                             PartService partService, CarService carService,
                             CustomerService customerService, SaleService saleService,
                             XmlParser xmlParser) {
    this.fileIoUtil = fileIoUtil;
    this.supplierService = supplierService;
    this.partService = partService;
    this.carService = carService;
    this.customerService = customerService;
    this.saleService = saleService;
    this.xmlParser = xmlParser;
  }

  @Override
  public void run(String... args) throws IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Please enter \"seed\" to seed into database "
            + "or number from to 1 to 6 for execute queries:");
    String command = reader.readLine().toLowerCase();

    switch (command) {

      case "seed":
        this.seedDatabase();
        break;
      case "1":
        this.getAllCustomersOrderedByBirthDate();
        break;
      case "2":
        this.getAllCarsByMake();
        break;
      case "3":
        this.getAllLocalSuppliers();
        break;
      case "4":
        this.getCarsAndTheirParts();
        break;
      case "5":
        this.getCustomersAndTheirPurchase();
        break;
      case "6":
        this.getSalesDetails();
        break;
      case "stop":
        System.exit(0);
        break;
      default:
        System.out.println("Valid commands:\n"
                + "\"seed\" -> seed database\n"
                + "Number from 1 to 6 -> execute query\n"
                + "\"stop\" -> stop application");
        break;
    }
  }

  private void seedDatabase() throws IOException {
    this.seedSuppliers();
    this.seedParts();
    this.seedCars();
    this.seedCustomers();
    this.seedSales();
  }

  private void seedSuppliers() throws IOException {
    this.supplierService.seedSuppliers(
            this.fileIoUtil.readFileContent(SUPPLIER_XML_INPUT_FILE_PATH));
  }

  private void seedParts() throws IOException {
    this.partService.seedParts(
            this.fileIoUtil.readFileContent(PARTS_XML_INPUT_FILE_PATH));
  }

  private void seedCars() throws IOException {
    this.carService.seedCars(
            this.fileIoUtil.readFileContent(CARS_XML_INPUT_FILE_PATH));
  }

  private void seedCustomers() throws IOException {
    this.customerService.seedCustomers(
            this.fileIoUtil.readFileContent(CUSTOMERS_XML_INPUT_FILE_PATH));
  }

  private void seedSales() {
    this.saleService.seedCustomers();
  }

  private void getAllCustomersOrderedByBirthDate() throws IOException {
    List<OrderedCustomersDto> allCustomers =
            this.customerService.getAllCustomersOrderedByBirthDate();

    OrderedCustomerRootDto orderedCustomerRootDto = new OrderedCustomerRootDto();
    orderedCustomerRootDto.setCustomersDtoList(allCustomers);

    this.xmlParser.objectToXmlFile(orderedCustomerRootDto,
            this.fileIoUtil.writeFileContent(ORDERED_CUSTOMERS_OUTPUT_FILE_PATH));
  }

  private void getAllCarsByMake() throws IOException {

    List<CarViewDto> carViewDtos = this.carService.getAllCarByMake(TOYOTA_CAR);

    CarViewRootDto carViewRootDto = new CarViewRootDto();
    carViewRootDto.setCarViewDtos(carViewDtos);

    this.xmlParser.objectToXmlFile(carViewRootDto,
            this.fileIoUtil.writeFileContent(TOYOTA_CARS_OUTPUT_FILE_PATH));
  }

  private void getAllLocalSuppliers() throws IOException {
    List<LocalSuppliersDto> localSuppliersDtos = this.supplierService.localSuppliers();

    LocalSupplierRootDto localSupplierRootDto = new LocalSupplierRootDto();
    localSupplierRootDto.setLocalSuppliersDtos(localSuppliersDtos);

    this.xmlParser.objectToXmlFile(localSupplierRootDto,
            this.fileIoUtil.writeFileContent(LOCAL_SUPPLIER_OUTPUT_FILE_PATH));
  }

  private void getCarsAndTheirParts() throws IOException {
    List<CarsWithPartsDto> carsWithPartsDtos = this.carService.getAllCarsWithTheirPart();

    CarsWithPartsRootDto cars = new CarsWithPartsRootDto();
    cars.setCarsWithPartsDtos(carsWithPartsDtos);

    this.xmlParser.objectToXmlFile(cars,
            this.fileIoUtil.writeFileContent(CAR_AND_PART_OUTPUT_FILE_PATH));
  }

  private void getCustomersAndTheirPurchase() throws IOException {
    List<CustomersWithPurchasesDto> customersWithPurchasesDtos =
            this.customerService.getCustomersPurchases();

    CustomersWithPurchasesRootDto customers = new CustomersWithPurchasesRootDto();
    customers.setCustomers(customersWithPurchasesDtos);

    this.xmlParser.objectToXmlFile(customers,
            this.fileIoUtil.writeFileContent(CUSTOMERS_AND_PURCHASES_OUTPUT_FILE_PATH));
  }

  private void getSalesDetails() throws IOException {
    List<SalesDiscountsDto> salesDiscountsDtos =
            this.saleService.getSalesDetails();

    SalesDiscountsRootDto sales = new SalesDiscountsRootDto();
    sales.setSales(salesDiscountsDtos);

    this.xmlParser.objectToXmlFile(sales,
            this.fileIoUtil.writeFileContent(SALES_DETAILS_OUTPUT_FILE_PATH));
  }
}
