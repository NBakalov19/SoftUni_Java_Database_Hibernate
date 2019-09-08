package com.bakalov.jsonparsecardealer.services;

import com.bakalov.jsonparsecardealer.domains.dtos.bind.CustomerSeedDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.CustomersWithPurchasesDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.OrderedCustomersDto;
import com.bakalov.jsonparsecardealer.domains.entities.Customer;

import java.util.List;

public interface CustomerService {

  void seedCustomers(CustomerSeedDto[] customerSeedDtos);

  List<Customer> getAll();

  List<OrderedCustomersDto> getAllCustomersOrderedByBirthDate();

  List<CustomersWithPurchasesDto> getCustomersPurchases();

}
