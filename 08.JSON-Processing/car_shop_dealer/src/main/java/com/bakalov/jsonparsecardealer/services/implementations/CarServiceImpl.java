package com.bakalov.jsonparsecardealer.services.implementations;

import com.bakalov.jsonparsecardealer.domains.dtos.bind.CarSeedDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.CarViewWithIdDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.CarWithPartsDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.PartNameAndPriceDto;
import com.bakalov.jsonparsecardealer.domains.entities.Car;
import com.bakalov.jsonparsecardealer.domains.entities.Part;
import com.bakalov.jsonparsecardealer.repositories.CarRepository;
import com.bakalov.jsonparsecardealer.repositories.PartRepository;
import com.bakalov.jsonparsecardealer.services.CarService;
import com.bakalov.jsonparsecardealer.utils.Constants;
import com.bakalov.jsonparsecardealer.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

  @Autowired
  public CarServiceImpl(ValidatorUtil validatorUtil,
                        ModelMapper mapper,
                        CarRepository carRepository,
                        PartRepository partRepository) {
    this.validatorUtil = validatorUtil;
    this.mapper = mapper;
    this.carRepository = carRepository;
    this.partRepository = partRepository;
  }

  @Override
  public void seedCars(CarSeedDto[] carSeedDtos) {
    for (CarSeedDto carSeedDto : carSeedDtos) {
      if (!this.validatorUtil.isValid(carSeedDto)) {
        this.validatorUtil.violations(carSeedDto)
                .forEach(v -> System.out.println(v.getMessage()));
        continue;
      }

      Car car = this.mapper.map(carSeedDto, Car.class);
      car.setParts(this.getRandomParts());

      this.carRepository.saveAndFlush(car);
    }
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
  public List<Car> getAll() {
    return this.carRepository.findAll();
  }

  @Override
  public List<CarViewWithIdDto> getCarsByMake(String make) {
    return this.carRepository.getAllByMakeOrderByTravelledDistanceDesc(make)
            .stream()
            .map(car -> this.mapper.map(car, CarViewWithIdDto.class))
            .collect(Collectors.toList());
  }

  @Override
  public List<CarWithPartsDto> getAllCarsWithTheirPart() {

    return this.carRepository.findAll()
            .stream()
            .map(car -> {
              CarWithPartsDto carWithPartsDto = this.mapper.map(car, CarWithPartsDto.class);

              carWithPartsDto.setParts(
                      car.getParts()
                              .stream()
                              .map(part -> this.mapper.map(part, PartNameAndPriceDto.class))
                              .collect(Collectors.toList()));

              return carWithPartsDto;
            })
            .collect(Collectors.toList());

  }
}
