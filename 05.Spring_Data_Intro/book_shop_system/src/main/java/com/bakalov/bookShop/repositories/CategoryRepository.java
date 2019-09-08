package com.bakalov.bookShop.repositories;

import com.bakalov.bookShop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
