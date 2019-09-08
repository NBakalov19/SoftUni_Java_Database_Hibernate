package com.bakalov.jsonexr.repositories;

import com.bakalov.jsonexr.domains.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
