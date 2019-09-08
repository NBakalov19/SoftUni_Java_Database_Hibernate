package com.bakalov.xmlproccesing.services;

import com.bakalov.xmlproccesing.domains.dtos.views.CustomersWithPurchasesDto;
import com.bakalov.xmlproccesing.domains.dtos.views.OrderedCustomersDto;
import com.bakalov.xmlproccesing.domains.entities.Customer;

import java.io.BufferedReader;
import java.util.List;

public interface CustomerService {

  void seedCustomers(BufferedReader reader);

  List<Customer> getAll();

  List<OrderedCustomersDto> getAllCustomersOrderedByBirthDate();

  List<CustomersWithPurchasesDto> getCustomersPurchases();
}
