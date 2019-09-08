package com.bakalov.gamestoredatabase.services.implementations;

import com.bakalov.gamestoredatabase.domains.dtos.GameAddDto;
import com.bakalov.gamestoredatabase.domains.dtos.GameDetailsDto;
import com.bakalov.gamestoredatabase.domains.dtos.GameTitleAndPriceDto;
import com.bakalov.gamestoredatabase.domains.dtos.GameTitleDto;
import com.bakalov.gamestoredatabase.domains.entities.Game;
import com.bakalov.gamestoredatabase.domains.entities.Role;
import com.bakalov.gamestoredatabase.domains.entities.User;
import com.bakalov.gamestoredatabase.repositories.GameRepository;
import com.bakalov.gamestoredatabase.repositories.UserRepository;
import com.bakalov.gamestoredatabase.services.GameService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;


@Service
public class GameServiceImpl extends BaseService implements GameService {

  private GameRepository gameRepository;

  public GameServiceImpl(StringBuilder builder, ModelMapper mapper,
                         Validator validator, UserRepository userRepository,
                         GameRepository gameRepository) {
    super(builder, mapper, validator, userRepository);
    this.gameRepository = gameRepository;
  }

  @Override
  public String addGame(GameAddDto gameAddDto) {
    this.builder.setLength(0);

    Game game = this.mapper.map(gameAddDto, Game.class);

    Set<ConstraintViolation<Game>> violations = this.validator.validate(game);

    if (violations.size() > 0) {
      for (ConstraintViolation<Game> violation : violations) {
        this.builder.append(violation.getMessage()).append(System.lineSeparator());
      }
      return this.builder.toString();
    }

    if (this.isUserAdmin().isEmpty()) {
      if (this.gameRepository.existsByTitle(game.getTitle())) {

        return this.builder
                .append(String.format("%s already exist.", game.getTitle()))
                .append(System.lineSeparator())
                .toString();
      }

      this.gameRepository.saveAndFlush(game);

      this.builder.append(String.format("Added %s", game.getTitle()));

      return this.builder.append(System.lineSeparator()).toString();
    } else {
      return this.builder.append(isUserAdmin()).toString();
    }
  }

  @Override
  public String editGame(Integer id, List<String> arguments) {

    if (isUserAdmin().isEmpty()) {
      Game game = this.gameRepository.getById(id).orElse(null);

      if (game == null) {
        return "Game not exist\n";
      }

      for (String argument : arguments) {
        String[] tokens = argument.split("=");
        String property = tokens[0];
        String value = tokens[1];

        switch (property) {
          case " title":
            game.setTitle(value);
            break;
          case "trailer":
            game.setTrailer(value);
            break;
          case "imageThumbnail":
            game.setImageThumbnail(value);
            break;
          case "size":
            game.setSize(Double.valueOf(value));
            break;
          case "price":
            game.setPrice(new BigDecimal(value));
            break;
          case "description":
            game.setDescription(value);
            break;
          case "releaseDate":
            game.setReleaseDate(LocalDate.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            break;
          default:
            return "Invalid property to update\n";
        }
      }

      Set<ConstraintViolation<Game>> violations = this.validator.validate(game);

      if (violations.size() > 0) {
        for (ConstraintViolation<Game> violation : violations) {
          this.builder.append(violation.getMessage()).append(System.lineSeparator());
        }

      } else {
        this.gameRepository.saveAndFlush(game);

        this.builder.append(String.format("Edited %s", game.getTitle()));
      }

    } else {
      return this.builder.append(isUserAdmin()).toString();
    }

    return this.builder.append(System.lineSeparator()).toString();
  }

  @Override
  public String deleteGame(Integer id) {
    if (isUserAdmin().isEmpty()) {
      Game game = this.gameRepository.getById(id).orElse(null);

      if (game == null) {
        return "Game not exist\n";
      }

      this.gameRepository.deleteById(id);
      this.gameRepository.flush();

      return String.format("Deleted %s\n", game.getTitle());
    } else {
      return this.builder.append(isUserAdmin()).toString();
    }
  }

  @Override
  public List<String> getAllGamesTitleAndPrice() {
    return this.gameRepository.findAll()
            .stream()
            .map(game -> this.mapper.map(game, GameTitleAndPriceDto.class))
            .map(dto -> String.format("%s %.2f", dto.getTitle(), dto.getPrice()))
            .collect(Collectors.toList());
  }

  @Override
  public String getDetailsForGameByTitle(String title) {
    GameDetailsDto game = this.gameRepository.getByTitle(title)
            .map(game1 -> this.mapper.map(game1, GameDetailsDto.class))
            .orElse(null);

    if (game == null) {
      return "Game not exist.\n";
    }

    return String
            .format("Title: %s\n" +
                            "Price: %.2f\n" +
                            "Description: %s\n" +
                            "Release date: %s",
                    game.getTitle(),
                    game.getPrice(),
                    game.getDescription(),
                    game.getReleaseDate());
  }

  @Override
  public List<String> getOwnedGames() {

    User user = this.userRepository.getOne(super.getLoggedUser().getId());

    List<String> ownedGames = new ArrayList<>();

    if (user != null){
      ownedGames = user.getGames()
              .stream()
              .map(game -> this.mapper.map(game,GameTitleDto.class))
              .map(dto -> String.format("%s",dto.getTitle()))
              .collect(Collectors.toList());
    }

   return ownedGames;
  }

  private String isUserAdmin() {
    User user = this.userRepository.getUserByEmail(super.getLoggedUser().getEmail()).orElse(null);

    if (user == null) {
      return "Only admin users can manage games\n";
    }

    if (!user.getRole().equals(Role.ADMIN)) {
      return String.format("%s is not admin!\n", user.getFullName());
    }
    return "";
  }
}