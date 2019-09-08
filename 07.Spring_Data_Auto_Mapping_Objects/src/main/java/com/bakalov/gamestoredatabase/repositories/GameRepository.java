package com.bakalov.gamestoredatabase.repositories;

import com.bakalov.gamestoredatabase.domains.dtos.GameDetailsDto;
import com.bakalov.gamestoredatabase.domains.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

  Boolean existsByTitle(String title);

  Optional<Game> getById(Integer id);

  Optional<Game> getByTitle(String title);

  void deleteById(Integer id);


}
