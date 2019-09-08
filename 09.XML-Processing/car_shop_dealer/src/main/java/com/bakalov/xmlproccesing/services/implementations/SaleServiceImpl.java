package com.bakalov.xmlproccesing.services.implementations;

import com.bakalov.xmlproccesing.domains.dtos.views.CarViewDto;
import com.bakalov.xmlproccesing.domains.dtos.views.SalesDiscountsDto;
import com.bakalov.xmlproccesing.domains.entities.Car;
import com.bakalov.xmlproccesing.domains.entities.Customer;
import com.bakalov.xmlproccesing.domains.entities.Part;
import com.bakalov.xmlproccesing.domains.entities.Sale;
import com.bakalov.xmlproccesing.repositories.SaleRepository;
import com.bakalov.xmlproccesing.services.CarService;
import com.bakalov.xmlproccesing.services.CustomerService;
import com.bakalov.xmlproccesing.services.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

  private final ModelMapper mapper;
  private final SaleRepository saleRepository;
  private final CarService carService;
  private final CustomerService customerService;

  @Autowired
  public SaleServiceImpl(ModelMapper mapper, SaleRepository saleRepository,
                         CarService carService, CustomerService customerService) {
    this.mapper = mapper;
    this.saleRepository = saleRepository;
    this.carService = carService;
    this.customerService = customerService;
  }

  @Override
  public void seedCustomers() {
    List<Car> cars = this.carService.getAll();
    List<Customer> customers = this.customerService.getAll();

    Random random = new Random();

    final BigDecimal[] discounts = {BigDecimal.ZERO,
            BigDecimal.valueOf(0.05),
            BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.15),
            BigDecimal.valueOf(0.2),
            BigDecimal.valueOf(0.3),
            BigDecimal.valueOf(0.4),
            BigDecimal.valueOf(0.5)
    };

    List<Sale> sales = new LinkedList<>();

    for (Car car : cars) {
      Sale sale = new Sale();
      sale.setCar(car);
      sale.setCustomer(customers.get(random.nextInt(customers.size())));

      if (sale.getCustomer().getYoungDriver()) {
        sale.setDiscount(discounts[random.nextInt(discounts.length)].add(BigDecimal.valueOf(0.05)));
      } else {
        sale.setDiscount(discounts[random.nextInt(discounts.length)]);
      }

      sales.add(sale);
    }

    this.saleRepository.saveAll(sales);
  }

  @Override
  public List<SalesDiscountsDto> getSalesDetails() {
    return this.saleRepository
            .findAll()
            .stream()
            .map(sale -> {
              SalesDiscountsDto salesDiscountsDto = new SalesDiscountsDto();
              salesDiscountsDto.setCar(this.mapper.map(sale.getCar(), CarViewDto.class));
              salesDiscountsDto.setCustomerName(sale.getCustomer().getName());

              salesDiscountsDto.setDiscount(sale.getDiscount());

              salesDiscountsDto.setPrice(sale.getCar()
                      .getParts()
                      .stream()
                      .map(Part::getPrice)
                      .reduce(BigDecimal.ZERO, BigDecimal::add));

              salesDiscountsDto.setPriceWithDiscount(
                      salesDiscountsDto.getPrice()
                              .multiply(
                                      BigDecimal.valueOf(1.0).subtract(salesDiscountsDto.getDiscount())));

              return salesDiscountsDto;
            })
            .collect(Collectors.toList());

  }
}