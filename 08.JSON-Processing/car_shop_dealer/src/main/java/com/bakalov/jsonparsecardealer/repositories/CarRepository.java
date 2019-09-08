package com.bakalov.jsonparsecardealer.repositories;

import com.bakalov.jsonparsecardealer.domains.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

  List<Car> getAllByMakeOrderByTravelledDistanceDesc(String make);

}