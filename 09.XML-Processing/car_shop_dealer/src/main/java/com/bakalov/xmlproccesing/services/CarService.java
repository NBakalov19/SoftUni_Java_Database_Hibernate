package com.bakalov.xmlproccesing.services;

import com.bakalov.xmlproccesing.domains.dtos.views.CarViewDto;
import com.bakalov.xmlproccesing.domains.dtos.views.CarsWithPartsDto;
import com.bakalov.xmlproccesing.domains.entities.Car;

import java.io.BufferedReader;
import java.util.List;

public interface CarService {

  void seedCars(BufferedReader reader);

  List<Car> getAll();

  List<CarViewDto> getAllCarByMake(String make);

  List<CarsWithPartsDto> getAllCarsWithTheirPart();
}
