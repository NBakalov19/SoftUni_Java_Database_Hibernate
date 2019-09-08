package com.bakalov.jsonexr.repositories;

import com.bakalov.jsonexr.domains.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal more, BigDecimal less);
}
