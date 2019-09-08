package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.CarImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static mostwanted.common.Constants.*;

@Service
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;
  private final RacerRepository racerRepository;
  private final FileUtil fileUtil;
  private final ValidationUtil validationUtil;
  private final ModelMapper mapper;
  private final Gson gson;


  @Autowired
  public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository,
                        FileUtil fileUtil, ValidationUtil validationUtil,
                        ModelMapper mapper, Gson gson) {
    this.carRepository = carRepository;
    this.racerRepository = racerRepository;
    this.fileUtil = fileUtil;
    this.validationUtil = validationUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean carsAreImported() {
    return this.carRepository.count() > 0;
  }

  @Override
  public String readCarsJsonFile() throws IOException {
    return this.fileUtil.readFile(CARS_JSON_FILE_PATH);
  }

  @Override
  public String importCars(String carsFileContent) {

    StringBuilder sb = new StringBuilder();

    CarImportDto[] dtos = this.gson.fromJson(carsFileContent, CarImportDto[].class);

    for (CarImportDto dto : dtos) {

      Car car = this.mapper.map(dto, Car.class);

      if (!this.validationUtil.isValid(car)) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }


      Racer racer = this.racerRepository.findByName(dto.getRacerName());

      if (racer == null) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      car.setRacer(racer);

      if (this.carRepository.findByBrandAndModelAndRacerAndPrice(
              car.getBrand(), car.getModel(), car.getRacer(), car.getPrice()) != null) {
        sb.append(DUPLICATE_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      this.carRepository.saveAndFlush(car);

      String carInfo = String.format("%s %s @ %d",
              car.getBrand(),
              car.getModel(),
              car.getYearOfProduction());

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              car.getClass().getSimpleName(),
              carInfo))
              .append(SEPARATOR);
    }
    return sb.toString().trim();
  }
}
