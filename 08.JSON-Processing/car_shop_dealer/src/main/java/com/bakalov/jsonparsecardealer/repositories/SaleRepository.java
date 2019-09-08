package com.bakalov.jsonparsecardealer.repositories;

import com.bakalov.jsonparsecardealer.domains.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
