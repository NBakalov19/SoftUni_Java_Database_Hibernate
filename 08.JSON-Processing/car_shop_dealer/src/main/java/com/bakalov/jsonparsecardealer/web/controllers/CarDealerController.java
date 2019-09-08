package com.bakalov.jsonparsecardealer.web.controllers;

import com.bakalov.jsonparsecardealer.domains.dtos.bind.CarSeedDto;
import com.bakalov.jsonparsecardealer.domains.dtos.bind.CustomerSeedDto;
import com.bakalov.jsonparsecardealer.domains.dtos.bind.PartSeedDto;
import com.bakalov.jsonparsecardealer.domains.dtos.bind.SupplierSeedDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.*;
import com.bakalov.jsonparsecardealer.services.*;
import com.bakalov.jsonparsecardealer.utils.Constants;
import com.bakalov.jsonparsecardealer.utils.FileIoUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class CarDealerController implements CommandLineRunner {

  private final Gson gson;
  private final FileIoUtil fileIoUtil;
  private final SupplierService supplierService;
  private final PartService partService;
  private final CarService carService;
  private final CustomerService customerService;
  private final SaleService saleService;

  @Autowired
  public CarDealerController(Gson gson,
                             FileIoUtil fileIoUtil,
                             SupplierService supplierService,
                             PartService partService, CarService carService,
                             CustomerService customerService, SaleService saleService) {
    this.gson = gson;
    this.fileIoUtil = fileIoUtil;
    this.supplierService = supplierService;
    this.partService = partService;
    this.carService = carService;
    this.customerService = customerService;
    this.saleService = saleService;
  }

  @Override
  public void run(String... args) throws Exception {

    //    this.seedSuppliers();
    //    this.seedParts();
    //    this.seedCars();
    //    this.seedCustomers();
    //    this.seedSales();

    this.getAllCustomersOrderedByBirthDate();
    //    this.getAllCarsByMake();
    //    this.getAllLocalSuppliers();
    //    this.getCarsAndTheirParts();
    //    this.getCustomersAndTheirPurchase();
    //    this.getSalesDetails();
  }

  private void seedSuppliers() throws IOException {

    String supplierContent =
            this.fileIoUtil.readFileContent(Constants.SUPPLIER_JSON_INPUT_FILE_PATH);

    SupplierSeedDto[] supplierSeedDtos =
            this.gson.fromJson(supplierContent, SupplierSeedDto[].class);

    this.supplierService.seedSuppliers(supplierSeedDtos);
  }

  private void seedParts() throws IOException {

    String partContent = this.fileIoUtil.readFileContent(Constants.PARTS_JSON_INPUT_FILE_PATH);

    PartSeedDto[] partSeedDtos = this.gson.fromJson(partContent, PartSeedDto[].class);

    this.partService.seedParts(partSeedDtos);
  }

  private void seedCars() throws IOException {
    String carsContent = this.fileIoUtil.readFileContent(Constants.CARS_JSON_INPUT_FILE_PATH);

    CarSeedDto[] carSeedDtos = this.gson.fromJson(carsContent, CarSeedDto[].class);

    this.carService.seedCars(carSeedDtos);
  }

  private void seedCustomers() throws IOException {
    String customersContent =
            this.fileIoUtil.readFileContent(Constants.CUSTOMERS_JSON_INPUT_FILE_PATH);

    CustomerSeedDto[] customerSeedDto =
            this.gson.fromJson(customersContent, CustomerSeedDto[].class);

    this.customerService.seedCustomers(customerSeedDto);
  }

  private void seedSales() throws IOException {
    this.saleService.seedSales();
  }

  private void getAllCustomersOrderedByBirthDate() throws IOException {

    List<OrderedCustomersDto> orderedCustomersDtos =
            this.customerService.getAllCustomersOrderedByBirthDate();

    String customers = this.gson.toJson(orderedCustomersDtos);

    this.fileIoUtil.writeFileContent(customers, Constants.ORDERED_CUSTOMERS_OUTPUT_FILE_PATH);

  }

  private void getAllCarsByMake() throws IOException {
    List<CarViewWithIdDto> carsByMake =
            this.carService.getCarsByMake(Constants.TOYOTA_CAR);

    String cars = this.gson.toJson(carsByMake);

    this.fileIoUtil.writeFileContent(cars, Constants.TOYOTA_CARS_OUTPUT_FILE_PATH);
  }

  private void getAllLocalSuppliers() throws IOException {

    List<LocalSuppliersDto> localSuppliersDtos =
            this.supplierService.localSuppliers();

    String suppliers = this.gson.toJson(localSuppliersDtos);

    this.fileIoUtil.writeFileContent(suppliers, Constants.LOCAL_SUPPLIER_OUTPUT_FILE_PATH);
  }

  private void getCarsAndTheirParts() throws IOException {
    List<CarWithPartsDto> carWithPartsDtos =
            this.carService.getAllCarsWithTheirPart();

    String carsWithParts = this.gson.toJson(carWithPartsDtos);

    this.fileIoUtil.writeFileContent(carsWithParts, Constants.CAR_AND_PART_OUTPUT_FILE_PATH);
  }

  private void getCustomersAndTheirPurchase() throws IOException {

    List<CustomersWithPurchasesDto> customersWithPurchasesDtos =
            this.customerService.getCustomersPurchases();

    String customers = this.gson.toJson(customersWithPurchasesDtos);

    this.fileIoUtil.writeFileContent(customers, Constants.CUSTOMERS_AND_PURCHASES_OUTPUT_FILE_PATH);
  }

  private void getSalesDetails() throws IOException {

    List<SalesDiscountsDto> salesDiscountsDtos =
            this.saleService.getSalesDetails();

    String salesDetails = this.gson.toJson(salesDiscountsDtos);

    this.fileIoUtil.writeFileContent(salesDetails, Constants.SALES_DETAILS_OUTPUT_FILE_PATH);
  }
}
