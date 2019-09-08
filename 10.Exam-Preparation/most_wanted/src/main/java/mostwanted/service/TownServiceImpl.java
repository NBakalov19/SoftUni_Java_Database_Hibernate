package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.TownImportDto;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static mostwanted.common.Constants.*;

@Service
public class TownServiceImpl implements TownService {


  private final TownRepository townRepository;
  private final FileUtil fileUtil;
  private final ValidationUtil validationUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  @Autowired
  public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil,
                         ValidationUtil validationUtil, ModelMapper mapper, Gson gson) {
    this.townRepository = townRepository;
    this.fileUtil = fileUtil;
    this.validationUtil = validationUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean townsAreImported() {
    return this.townRepository.count() > 0;
  }

  @Override
  public String readTownsJsonFile() throws IOException {
    return this.fileUtil.readFile(TOWNS_JSON_FILE_PATH);
  }

  @Override
  public String importTowns(String townsFileContent) {
    StringBuilder sb = new StringBuilder();

    TownImportDto[] dtos = this.gson.fromJson(townsFileContent, TownImportDto[].class);

    for (TownImportDto dto : dtos) {

      Town town = this.mapper.map(dto, Town.class);

      if (!this.validationUtil.isValid(town)) {
        sb.append(Constants.INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }


      if (this.townRepository.findByName(town.getName()) != null) {
        sb.append(Constants.DUPLICATE_DATA_MESSAGE);

        continue;
      }

      this.townRepository.saveAndFlush(town);
      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              town.getClass().getSimpleName(),
              town.getName()))
              .append(SEPARATOR);
    }


    return sb.toString().trim();
  }

  @Override
  public String exportRacingTowns() {
    StringBuilder sb = new StringBuilder();

    List<Town> racingTowns = this.townRepository.getAllByRaces();

    for (Town racingTown : racingTowns) {
      sb.append(String.format("Name: %s", racingTown.getName())).append(SEPARATOR);
      sb.append(String.format("Racers: %d", racingTown.getRacers().size())).append(SEPARATOR);
      sb.append(SEPARATOR);
    }

    return sb.toString().trim();

  }
}
