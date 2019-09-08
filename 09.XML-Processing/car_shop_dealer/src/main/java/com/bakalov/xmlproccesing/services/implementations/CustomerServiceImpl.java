package com.bakalov.xmlproccesing.services.implementations;

import com.bakalov.xmlproccesing.domains.dtos.binds.CustomerSeedDto;
import com.bakalov.xmlproccesing.domains.dtos.binds.roots.CustomerRootDto;
import com.bakalov.xmlproccesing.domains.dtos.views.CustomersWithPurchasesDto;
import com.bakalov.xmlproccesing.domains.dtos.views.OrderedCustomersDto;
import com.bakalov.xmlproccesing.domains.entities.Customer;
import com.bakalov.xmlproccesing.domains.entities.Part;
import com.bakalov.xmlproccesing.repositories.CustomerRepository;
import com.bakalov.xmlproccesing.services.CustomerService;
import com.bakalov.xmlproccesing.utils.ValidatorUtil;
import com.bakalov.xmlproccesing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final CustomerRepository customerRepository;
  private final XmlParser xmlParser;

  @Autowired
  public CustomerServiceImpl(ValidatorUtil validatorUtil, ModelMapper mapper,
                             CustomerRepository customerRepository, XmlParser xmlParser) {
    this.validatorUtil = validatorUtil;
    this.mapper = mapper;
    this.customerRepository = customerRepository;
    this.xmlParser = xmlParser;
  }

  @Override
  public void seedCustomers(BufferedReader reader) {
    CustomerRootDto uncheckedCustomerRootDto = this.xmlParser.parseXml(CustomerRootDto.class, reader);

    CustomerRootDto checkedCustomerRootDto = new CustomerRootDto();
    for (CustomerSeedDto customerSeedDto : uncheckedCustomerRootDto.getCustomerSeedDtos()) {
      if (!this.validatorUtil.isValid(customerSeedDto)) {
        this.validatorUtil.violations(customerSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }
      checkedCustomerRootDto.getCustomerSeedDtos().add(customerSeedDto);
    }

    checkedCustomerRootDto.getCustomerSeedDtos()
            .stream()
            .map(dto -> this.mapper.map(dto, Customer.class))
            .forEach(this.customerRepository::saveAndFlush);
  }

  @Override
  public List<Customer> getAll() {
    return this.customerRepository.findAll();
  }

  @Override
  public List<OrderedCustomersDto> getAllCustomersOrderedByBirthDate() {

    return this.customerRepository.getAllOrderByBirthDateAndIsYoungDriver()
            .stream()
            .map(customer -> this.mapper.map(customer, OrderedCustomersDto.class))
            .collect(Collectors.toList());
  }

  @Override
  public List<CustomersWithPurchasesDto> getCustomersPurchases() {
    return this.customerRepository
            .findAll()
            .stream()
            .map(customer -> {

              CustomersWithPurchasesDto customersWithPurchasesDto = new CustomersWithPurchasesDto();

              customersWithPurchasesDto.setFullName(customer.getName());
              customersWithPurchasesDto.setBoughtCars(customer.getPurchases().size());

              BigDecimal moneySpent = customer.getPurchases()
                      .stream()
                      .map(sale -> sale.getCar()
                              .getParts()
                              .stream()
                              .map(Part::getPrice)
                              .reduce(BigDecimal.ZERO, BigDecimal::add))
                      .reduce(BigDecimal.ZERO, BigDecimal::add);

              customersWithPurchasesDto.setSpentMoney(moneySpent);
              return customersWithPurchasesDto;
            }).sorted((c1, c2) -> {
              int result = c2.getSpentMoney().compareTo(c1.getSpentMoney());

              if (result == 0) {
                result = c2.getBoughtCars().compareTo(c1.getBoughtCars());
              }
              return result;
            })
            .collect(Collectors.toList());

  }
}
