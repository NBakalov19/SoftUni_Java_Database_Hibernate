package com.bakalov.xmlproccesing.repositories;

import com.bakalov.xmlproccesing.domains.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {
}
