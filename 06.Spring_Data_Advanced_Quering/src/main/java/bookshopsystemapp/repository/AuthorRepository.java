package bookshopsystemapp.repository;

import bookshopsystemapp.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

  @Query("select a from Author as a where a.firstName like %:pattern")
  List<Author> findAuthorsByFirstNameEndsWith(String pattern);

  @Query("select concat(a.firstName, ' ',a.lastName, ' - ' , sum(b.copies)) " +
          "from Author as a " +
          "join a.books as b " +
          "group by a.id " +
          "order by sum(b.copies) desc")
  List<Object> getAllByCopies();

}
