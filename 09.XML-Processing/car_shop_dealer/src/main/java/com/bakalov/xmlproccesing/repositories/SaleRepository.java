package com.bakalov.xmlproccesing.repositories;

import com.bakalov.xmlproccesing.domains.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
