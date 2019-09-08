package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
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
public class RacerServiceImpl implements RacerService {


  private final RacerRepository racerRepository;
  private final TownRepository townRepository;
  private final FileUtil fileUtil;
  private final ValidationUtil validationUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  @Autowired
  public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository,
                          FileUtil fileUtil, ValidationUtil validationUtil,
                          ModelMapper mapper, Gson gson) {
    this.racerRepository = racerRepository;
    this.townRepository = townRepository;
    this.fileUtil = fileUtil;
    this.validationUtil = validationUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean racersAreImported() {
    return this.racerRepository.count() > 0;
  }

  @Override
  public String readRacersJsonFile() throws IOException {
    return this.fileUtil.readFile(RACERS_JSON_FILE_PATH);
  }

  @Override
  public String importRacers(String racersFileContent) {

    StringBuilder sb = new StringBuilder();

    RacerImportDto[] dtos = this.gson.fromJson(racersFileContent, RacerImportDto[].class);

    for (RacerImportDto dto : dtos) {

      Racer racer = this.mapper.map(dto, Racer.class);

      if (!this.validationUtil.isValid(racer)) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }


      if (this.racerRepository.findByName(racer.getName()) != null) {
        sb.append(DUPLICATE_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      Town town = this.townRepository.findByName(dto.getHomeTown());

      if (town == null) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      racer.setHomeTown(town);

      this.racerRepository.saveAndFlush(racer);
      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              racer.getClass().getSimpleName(),
              racer.getName()))
              .append(SEPARATOR);

    }

    return sb.toString().trim();
  }

  @Override
  public String exportRacingCars() {
    StringBuilder sb = new StringBuilder();

    List<Racer> racers = this.racerRepository.findAllByCars();


    for (Racer racer : racers) {

      sb.append(String.format("Name: %s", racer.getName())).append(SEPARATOR);

      if (racer.getAge() != null) {
        sb.append(String.format("Age: %d", racer.getAge())).append(SEPARATOR);
      }

      sb.append("Cars:").append(SEPARATOR);

      for (Car car : racer.getCars()) {
        sb.append(String.format("%s %s %d",
                car.getBrand(),
                car.getModel(),
                car.getYearOfProduction()))
                .append(SEPARATOR);
      }

      sb.append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}
