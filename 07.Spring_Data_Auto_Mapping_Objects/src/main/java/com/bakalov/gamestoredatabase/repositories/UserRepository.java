package com.bakalov.gamestoredatabase.repositories;

import com.bakalov.gamestoredatabase.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> getUserByEmail(String email);


}
