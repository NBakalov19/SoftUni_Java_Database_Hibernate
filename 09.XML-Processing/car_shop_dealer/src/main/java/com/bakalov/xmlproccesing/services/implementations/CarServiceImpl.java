package com.bakalov.xmlproccesing.services.implementations;

import com.bakalov.xmlproccesing.domains.dtos.binds.CarSeedDto;
import com.bakalov.xmlproccesing.domains.dtos.binds.roots.CarRootDto;
import com.bakalov.xmlproccesing.domains.dtos.views.CarViewDto;
import com.bakalov.xmlproccesing.domains.dtos.views.CarsWithPartsDto;
import com.bakalov.xmlproccesing.domains.dtos.views.PartNameAndPriceDto;
import com.bakalov.xmlproccesing.domains.entities.Car;
import com.bakalov.xmlproccesing.domains.entities.Part;
import com.bakalov.xmlproccesing.repositories.CarRepository;
import com.bakalov.xmlproccesing.repositories.PartRepository;
import com.bakalov.xmlproccesing.services.CarService;
import com.bakalov.xmlproccesing.utils.Constants;
import com.bakalov.xmlproccesing.utils.ValidatorUtil;
import com.bakalov.xmlproccesing.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
@Transactional
public class CarServiceImpl implements CarService {

  private final ValidatorUtil validatorUtil;
  private final ModelMapper mapper;
  private final CarRepository carRepository;
  private final PartRepository partRepository;
  private final XmlParser xmlParser;

  @Autowired
  public CarServiceImpl(ValidatorUtil validatorUtil,
                        ModelMapper mapper,
                        CarRepository carRepository,
                        PartRepository partRepository, XmlParser xmlParser) {
    this.validatorUtil = validatorUtil;
    this.mapper = mapper;
    this.carRepository = carRepository;
    this.partRepository = partRepository;
    this.xmlParser = xmlParser;
  }

  @Override
  public void seedCars(BufferedReader reader) {
    CarRootDto uncheckedCarRootDto = this.xmlParser.parseXml(CarRootDto.class, reader);

    CarRootDto checkedCarRootDto = new CarRootDto();
    for (CarSeedDto supplierSeedDto : uncheckedCarRootDto.getCarSeedDtos()) {
      if (!this.validatorUtil.isValid(supplierSeedDto)) {
        this.validatorUtil.violations(supplierSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }
      checkedCarRootDto.getCarSeedDtos().add(supplierSeedDto);
    }

    checkedCarRootDto.getCarSeedDtos()
            .stream()
            .map(carSeedDto -> {
              Car car = this.mapper.map(carSeedDto, Car.class);
              car.setParts(this.getRandomParts());

              return car;
            })
            .forEach(this.carRepository::saveAndFlush);
  }


  private Part getRandomPart() {
    Random random = new Random();

    int id = random.nextInt((int) this.partRepository.count() - 1) + 1;

    return this.partRepository.getOne(id);
  }

  private List<Part> getRandomParts() {
    Random random = new Random();
    List<Part> parts = new ArrayList<>();
    int size = random.nextInt(Constants.CAR_MAX_PARTS_BOUND - Constants.CAR_MIN_PARTS_BOUND + 1)
            + Constants.CAR_MIN_PARTS_BOUND;

    for (int i = 0; i < size; i++) {
      parts.add(this.getRandomPart());
    }

    return parts;
  }


  @Override
  public List<CarViewDto> getAllCarByMake(String make) {
    return this.carRepository.getAllByMakeOrderByTravelledDistanceDesc(make)
            .stream()
            .map(car -> this.mapper.map(car, CarViewDto.class))
            .collect(Collectors.toList());
  }

  @Override
  public List<Car> getAll() {
    return this.carRepository.findAll();
  }

  @Override
  public List<CarsWithPartsDto> getAllCarsWithTheirPart() {
    return this.carRepository.findAll()
            .stream()
            .map(car -> {
              CarsWithPartsDto carsWithPartsDto = this.mapper.map(car, CarsWithPartsDto.class);

              carsWithPartsDto.setPartNameAndPriceDtoSet(
                      car.getParts()
                              .stream()
                              .map(part -> this.mapper.map(part, PartNameAndPriceDto.class))
                              .collect(Collectors.toSet()));

              return carsWithPartsDto;
            })
            .collect(Collectors.toList());
  }
}
