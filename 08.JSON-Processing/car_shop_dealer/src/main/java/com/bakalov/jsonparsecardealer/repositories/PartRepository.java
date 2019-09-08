package com.bakalov.jsonparsecardealer.repositories;

import com.bakalov.jsonparsecardealer.domains.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {
}
