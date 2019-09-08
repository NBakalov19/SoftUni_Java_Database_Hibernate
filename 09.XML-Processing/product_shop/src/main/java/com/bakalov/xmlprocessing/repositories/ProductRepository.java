package com.bakalov.xmlprocessing.repositories;


import com.bakalov.xmlprocessing.domains.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal more, BigDecimal less);
}
