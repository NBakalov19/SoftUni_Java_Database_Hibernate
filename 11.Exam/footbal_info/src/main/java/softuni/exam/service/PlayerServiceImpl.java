package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.xml.json.PlayersImportDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.util.List;

import static softuni.exam.common.Constants.*;

@Service
public class PlayerServiceImpl implements PlayerService {

  private final PlayerRepository playerRepository;
  private final TeamRepository teamRepository;
  private final PictureRepository pictureRepository;
  private final ValidatorUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository,
                           PictureRepository pictureRepository, ValidatorUtil validatorUtil,
                           FileUtil fileUtil, ModelMapper mapper, Gson gson) {
    this.playerRepository = playerRepository;
    this.teamRepository = teamRepository;
    this.pictureRepository = pictureRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public String importPlayers() throws IOException {
    StringBuilder sb = new StringBuilder();

    PlayersImportDto[] players = this.gson.fromJson(this.readPlayersJsonFile(), PlayersImportDto[].class);

    for (PlayersImportDto playerDto : players) {

      if (!this.validatorUtil.isValid(playerDto)) {
        this.validatorUtil.violations(playerDto)
                .forEach(v -> sb.append(INCORRECT_PLAYER_MESSAGE).append(SEPARATOR));
      } else {

        Player player = this.mapper.map(playerDto, Player.class);

        Picture picture = this.pictureRepository.findByUrl(player.getPicture().getUrl()).orElse(null);

        Team team = this.teamRepository.findByName(player.getTeam().getName()).orElse(null);

        player.setPicture(picture);
        player.setTeam(team);

        if (!this.validatorUtil.isValid(player)) {
          this.validatorUtil.violations(playerDto)
                  .forEach(v -> sb.append(INCORRECT_PLAYER_MESSAGE).append(SEPARATOR));

          continue;
        }

        String playerFullName = String.format("%s %s", player.getFirstName(), player.getLastName());

        this.playerRepository.saveAndFlush(player);

        sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
                player.getClass().getSimpleName(),
                playerFullName))
                .append(SEPARATOR);
      }
    }

    return sb.toString();
  }

  @Override
  public boolean areImported() {
    return this.playerRepository.count() > 0;
  }

  @Override
  public String readPlayersJsonFile() throws IOException {

    return this.fileUtil.readFile(PLAYERS_FILE_INPUT_PATH);
  }

  @Override
  public String exportPlayersWhereSalaryBiggerThan() {
    StringBuilder sb = new StringBuilder();

    List<Player> players = this.playerRepository.findBySalaryAfterOrderBySalaryDesc(SALARY);

    for (Player player : players) {
      sb.append(String.format("Player name: %s %s", player.getFirstName(), player.getLastName()))
              .append(SEPARATOR);

      sb.append(String.format("\tNumber: %d", player.getNumber())).append(SEPARATOR);
      sb.append(String.format("\tSalary: %.2f", player.getSalary())).append(SEPARATOR);
      sb.append(String.format("\tTeam: %s", player.getTeam().getName())).append(SEPARATOR);

    }

    return sb.toString().trim();
  }

  @Override
  public String exportPlayersInATeam() {
    StringBuilder sb = new StringBuilder();

    Team team = this.teamRepository.findByName(TEAM_NAME).orElse(null);

    if (team != null) {
      List<Player> players = this.playerRepository.findByTeamOrderById(team);

      sb.append(String.format("Team: %s", team.getName())).append(SEPARATOR);

      for (Player player : players) {
        sb.append(String.format("Player name: %s %s - %s",
                player.getFirstName(), player.getLastName(), player.getPosition().toString())).append(SEPARATOR);
        sb.append(String.format("Player number: %d", player.getNumber())).append(SEPARATOR);
      }
    }

    return sb.toString().trim();
  }
}
