package bookshopsystemapp.repository;

import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

  List<Book> findAllByReleaseDateAfter(LocalDate date);

  List<Book> findAllByReleaseDateBefore(LocalDate date);

  List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

  List<Book> findAllByCopiesLessThan(Integer copies);

  List<Book> findAllByPriceIsLessThanOrPriceIsGreaterThan(BigDecimal minPrice, BigDecimal maxPrice);

  List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

  List<Book> findAllByTitleContains(String pattern);

  @Query("select b from Book as b where b.author.lastName like :pattern%")
  List<Book> findAllBooksByAuthorWhichLastNameStartsWith(String pattern);

  @Query("select b from Book as b where LENGTH(b.title) > :givenLength")
  List<Book> findAllByTitleLengthGreaterThan(int givenLength);

  List<Book> findAllByTitle(String title);

  @Transactional
  @Modifying
  @Query("update Book as b set b.copies = b.copies + :copies where b.releaseDate > :date")
  void updateBookCopiesWithReleaseDateAfter(LocalDate date, int copies);

  @Modifying
  @Query("delete from Book as b where b.copies < :minCopies")
  int deleteBookByCopiesLessThan(int minCopies);
}
