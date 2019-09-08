package com.bakalov.jsonparsecardealer.services;

import com.bakalov.jsonparsecardealer.domains.dtos.bind.CarSeedDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.CarViewWithIdDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.CarWithPartsDto;
import com.bakalov.jsonparsecardealer.domains.entities.Car;

import java.util.List;

public interface CarService {

  void seedCars(CarSeedDto[] carSeedDtos);

  List<Car> getAll();

  List<CarViewWithIdDto> getCarsByMake(String make);

  List<CarWithPartsDto> getAllCarsWithTheirPart();
}
