package com.bakalov.xmlproccesing.repositories;

import com.bakalov.xmlproccesing.domains.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  @Query("select c from Customer as c order by c.birthDate, c.isYoungDriver")
  List<Customer> getAllOrderByBirthDateAndIsYoungDriver();
}
