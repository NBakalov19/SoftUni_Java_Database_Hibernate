package com.bakalov.gamestoredatabase.controlers;

import com.bakalov.gamestoredatabase.domains.dtos.EditGameDto;
import com.bakalov.gamestoredatabase.domains.dtos.GameAddDto;
import com.bakalov.gamestoredatabase.domains.dtos.UserLoginDto;
import com.bakalov.gamestoredatabase.domains.dtos.UserRegisterDto;
import com.bakalov.gamestoredatabase.services.GameService;
import com.bakalov.gamestoredatabase.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GameStoreController implements CommandLineRunner {

  private final BufferedReader reader;
  private final UserService userService;
  private final GameService gameService;

  @Autowired
  public GameStoreController(BufferedReader reader, UserService userService, GameService gameService) {
    this.reader = reader;
    this.userService = userService;
    this.gameService = gameService;
  }


  @Override
  public void run(String... args) throws Exception {

    while (true) {
      String[] params = reader.readLine().split("\\|");
      String command = params[0];

      if ("RegisterUser".equals(command)) {
        String email = params[1];
        String password = params[2];
        String confirmPassword = params[3];
        String fullName = params[4];

        UserRegisterDto userRegisterDto =
                new UserRegisterDto(email, password, confirmPassword, fullName);

        System.out.print(this.userService.registerUser(userRegisterDto));

      } else if ("LoginUser".equals(command)) {

        String email = params[1];
        String password = params[2];

        System.out.print(this.userService.loginUser(
                new UserLoginDto(email, password)));

      } else if ("LogoutUser".equals(command)) {

        System.out.print(this.userService.logOut());

      } else if ("stop".equals(command.toLowerCase())) {

        System.exit(0);

      } else if ("AddGame".equals(command)) {

        String title = params[1];
        BigDecimal price = new BigDecimal(params[2]);
        Double size = Double.parseDouble(params[3]);
        String trailer = params[4];
        String thubnail = params[5];
        String description = params[6];
        LocalDate releaseDate = LocalDate.parse(params[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        GameAddDto game = new GameAddDto(title, trailer, thubnail, size, price, description, releaseDate);

        System.out.print(this.gameService.addGame(game));

      } else if ("EditGame".equals(command)) {

        Integer id = Integer.parseInt(params[1]);

        List<String> arguments = new ArrayList<>(Arrays.asList(params).subList(2, params.length));

        System.out.print(this.gameService.editGame(id, arguments));

      } else if ("DeleteGame".equals(command)) {
        Integer id = Integer.parseInt(params[1]);

        System.out.print(this.gameService.deleteGame(id));

      } else if ("AllGames".equals(command)) {

        this.gameService.getAllGamesTitleAndPrice()
                .forEach(System.out::println);

      } else if ("DetailGame".equals(command)) {

        String title = params[1];

        System.out.print(this.gameService.getDetailsForGameByTitle(title));
      } else if ("OwnedGames".equals(command)) {

        this.gameService.getOwnedGames()
                .forEach(System.out::println);
      } else {
        System.out.println("Enter valid command or enter stop to finish executing");
      }
    }
  }
}