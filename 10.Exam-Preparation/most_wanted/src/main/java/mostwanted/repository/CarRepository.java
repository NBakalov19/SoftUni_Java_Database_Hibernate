package mostwanted.repository;

import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

  Car findByBrandAndModelAndRacerAndPrice(String brand, String model, Racer racer, BigDecimal price);


}
