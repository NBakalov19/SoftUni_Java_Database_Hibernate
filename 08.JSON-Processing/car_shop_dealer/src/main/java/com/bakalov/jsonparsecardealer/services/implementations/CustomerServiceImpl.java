package com.bakalov.jsonparsecardealer.services.implementations;

import com.bakalov.jsonparsecardealer.domains.dtos.bind.CustomerSeedDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.CarViewDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.CustomersWithPurchasesDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.OrderedCustomersDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.SaleViewDto;
import com.bakalov.jsonparsecardealer.domains.entities.Customer;
import com.bakalov.jsonparsecardealer.domains.entities.Part;
import com.bakalov.jsonparsecardealer.repositories.CustomerRepository;
import com.bakalov.jsonparsecardealer.services.CustomerService;
import com.bakalov.jsonparsecardealer.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(ValidatorUtil validatorUtil, ModelMapper mapper,
                             CustomerRepository customerRepository) {
    this.validatorUtil = validatorUtil;
    this.mapper = mapper;
    this.customerRepository = customerRepository;
  }


  @Override
  public void seedCustomers(CustomerSeedDto[] customerSeedDtos) {
    for (CustomerSeedDto customerSeedDto : customerSeedDtos) {
      if (!this.validatorUtil.isValid(customerSeedDto)) {
        this.validatorUtil.violations(customerSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }

      Customer customer = this.mapper.map(customerSeedDto, Customer.class);

      this.customerRepository.saveAndFlush(customer);
    }
  }

  @Override
  public List<Customer> getAll() {
    return this.customerRepository.findAll();
  }

  @Override
  public List<OrderedCustomersDto> getAllCustomersOrderedByBirthDate() {

    return this.customerRepository.getAllByBirthDate()
            .stream()
            .map(customer -> {
              OrderedCustomersDto orderedCustomersDto =
                      this.mapper.map(customer, OrderedCustomersDto.class);
              orderedCustomersDto.setSales(
                      customer.getSales()
                              .stream()
                              .map(sale -> {

                                SaleViewDto saleViewDto = this.mapper.map(sale, SaleViewDto.class);
                                saleViewDto.setCar(
                                        this.mapper.map(sale.getCar(), CarViewDto.class));

                                return saleViewDto;

                              }).collect(Collectors.toSet()));

              return orderedCustomersDto;

            }).collect(Collectors.toList());
  }

  @Override
  public List<CustomersWithPurchasesDto> getCustomersPurchases() {

    return this.customerRepository
            .findAll()
            .stream()
            .map(customer -> {

              CustomersWithPurchasesDto customersWithPurchasesDto = new CustomersWithPurchasesDto();

              customersWithPurchasesDto.setFullName(customer.getName());
              customersWithPurchasesDto.setBoughtCars(customer.getSales().size());

              BigDecimal moneySpent = customer.getSales()
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
