package com.bakalov.jsonparsecardealer.repositories;

import com.bakalov.jsonparsecardealer.domains.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  @Query("select c from Customer as c order by c.birthDate, c.youngDriver")
  List<Customer> getAllByBirthDate();
}
