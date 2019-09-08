package com.bakalov.xmlprocessing.repositories;

import com.bakalov.xmlprocessing.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


  @Query("select u from User as u " +
          "join u.soldProducts as p " +
          "where p.buyer is not null " +
          "group by u.id " +
          "order by u.lastName, u.firstName")
  List<User> getAllBySoldProductsContainsProduct_Buyer();
}
