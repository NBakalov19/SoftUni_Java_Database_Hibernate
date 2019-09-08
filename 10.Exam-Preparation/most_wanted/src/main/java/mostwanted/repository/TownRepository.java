package mostwanted.repository;

import mostwanted.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {

  Town findByName(String name);

  @Query("select t from Town t " +
          "join Racer r on t.id = r.homeTown " +
          "group by t.id " +
          "order by size(t.racers) desc , t.id asc ")
  List<Town> getAllByRaces();
}
