package com.bakalov.bookShop.repositories;

import com.bakalov.bookShop.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
