package com.bakalov.jsonparsecardealer.repositories;

import com.bakalov.jsonparsecardealer.domains.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}