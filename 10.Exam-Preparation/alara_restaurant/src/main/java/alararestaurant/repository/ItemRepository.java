package alararestaurant.repository;

import alararestaurant.domain.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

  Item findByName(String name);
}
