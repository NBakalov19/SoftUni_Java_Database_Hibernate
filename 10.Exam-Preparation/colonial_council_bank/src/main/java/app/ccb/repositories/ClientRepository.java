package app.ccb.repositories;

import app.ccb.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

  Client findByFullName(String fullName);

  @Query("select c from Client c "
          + "join  c.bankAccount b "
          + "join b.cards "
          + "group by c.id "
          + "order by size(b.cards) desc")
  List<Client> findClientByBankAccount_CountOfCards();
}
