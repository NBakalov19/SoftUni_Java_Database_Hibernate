package bookshopsystemapp.service.implementations;

import bookshopsystemapp.domain.entities.*;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static bookshopsystemapp.util.Constants.*;


@Service
@Transactional
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final CategoryRepository categoryRepository;
  private final FileUtil fileUtil;
  private final BufferedReader reader;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository,
                         AuthorRepository authorRepository,
                         CategoryRepository categoryRepository,
                         FileUtil fileUtil,
                         BufferedReader reader) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.categoryRepository = categoryRepository;
    this.fileUtil = fileUtil;
    this.reader = reader;
  }

  @Override
  public void seedBooks() throws IOException {
    if (this.bookRepository.count() != 0) {
      return;
    }

    String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
    for (String line : booksFileContent) {
      String[] lineParams = line.split("\\s+");

      Book book = new Book();
      book.setAuthor(this.getRandomAuthor());

      EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
      book.setEditionType(editionType);
      LocalDate releaseDate =
              LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
      book.setReleaseDate(releaseDate);

      int copies = Integer.parseInt(lineParams[2]);
      book.setCopies(copies);

      BigDecimal price = new BigDecimal(lineParams[3]);
      book.setPrice(price);

      AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
      book.setAgeRestriction(ageRestriction);

      StringBuilder title = new StringBuilder();
      for (int i = 5; i < lineParams.length; i++) {
        title.append(lineParams[i]).append(" ");
      }

      book.setTitle(title.toString().trim());

      Set<Category> categories = this.getRandomCategories();
      book.setCategories(categories);

      this.bookRepository.saveAndFlush(book);
    }
  }

  @Override
  public List<String> getAllBooksTitlesAfter() {
    List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

    return books.stream().map(Book::getTitle).collect(Collectors.toList());
  }

  @Override
  public Set<String> getAllAuthorsWithBookBefore() {
    List<Book> books = this.bookRepository
            .findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

    return books.stream()
            .map(b -> String.format("%s %s",
                    b.getAuthor().getFirstName(),
                    b.getAuthor().getLastName()))
            .collect(Collectors.toSet());
  }

  private Author getRandomAuthor() {
    Random random = new Random();

    int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

    return this.authorRepository.findById(randomId).orElse(null);
  }

  private Set<Category> getRandomCategories() {
    Set<Category> categories = new LinkedHashSet<>();

    Random random = new Random();
    int length = random.nextInt(5);

    for (int i = 0; i < length; i++) {
      Category category = this.getRandomCategory();

      categories.add(category);
    }

    return categories;
  }

  private Category getRandomCategory() {
    Random random = new Random();

    int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

    return this.categoryRepository.findById(randomId).orElse(null);
  }

  @Override
  public List<String> getAllBookTitleByAgeRestriction() throws IOException {
    AgeRestriction ageRestriction = AgeRestriction.valueOf(reader.readLine().toUpperCase());

    return this.bookRepository
            .findAllByAgeRestriction(ageRestriction)
            .stream()
            .map(Book::getTitle)
            .collect(Collectors.toList());
  }

  @Override
  public List<String> getAllGoldenBookEditions() {

    return this.bookRepository
            .findAllByCopiesLessThan(GOLDEN_EDITION_BOOK_COPIES_VALUE)
            .stream()
            .map(Book::getTitle)
            .collect(Collectors.toList());
  }

  @Override
  public List<String> getAllBooksWithPriceBetween() {

    return this.bookRepository
            .findAllByPriceIsLessThanOrPriceIsGreaterThan(MIN_PRICE, MAX_PRICE)
            .stream()
            .map(book -> String.format("%s - $%.2f",
                    book.getTitle(),
                    book.getPrice()))
            .collect(Collectors.toList());
  }

  @Override
  public List<String> getAllBooksInNotGivenYear() throws IOException {
    String year = reader.readLine();

    String beforeYear = year + "-01-01";
    LocalDate before = LocalDate.parse(beforeYear, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    String afterYear = year + "-12-31";
    LocalDate after = LocalDate.parse(afterYear, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    return this.bookRepository
            .findAllByReleaseDateBeforeOrReleaseDateAfter(before, after)
            .stream()
            .map(Book::getTitle)
            .collect(Collectors.toList());
  }

  @Override
  public List<String> getAllBooksWithReleaseDateBefore() throws IOException {
    String date = reader.readLine().toLowerCase();

    List<Book> books = this.bookRepository
            .findAllByReleaseDateBefore(
                    LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")));

    return books
            .stream()
            .map(book -> String.format("%s %s %.2f",
                    book.getTitle(),
                    book.getEditionType().name(),
                    book.getPrice()))
            .collect(Collectors.toList());
  }

  @Override
  public List<String> getAllTitlesContainsPattern() throws IOException {
    String pattern = reader.readLine().toLowerCase();

    return this.bookRepository
            .findAllByTitleContains(pattern)
            .stream()
            .map(Book::getTitle)
            .collect(Collectors.toList());
  }

  @Override
  public List<String> getAllBooksFromAuthorWhereLastNameStarts() throws IOException {
    String pattern = reader.readLine();

    return this.bookRepository
            .findAllBooksByAuthorWhichLastNameStartsWith(pattern)
            .stream()
            .map(book ->
                    String.format("%s (%s %s)",
                            book.getTitle(),
                            book.getAuthor().getFirstName(),
                            book.getAuthor().getLastName()))
            .collect(Collectors.toList());
  }

  @Override
  public Integer countOfBookTitleLengthLongerByGivenLength() throws IOException {
    int givenLength = Integer.parseInt(reader.readLine());

    return this.bookRepository
            .findAllByTitleLengthGreaterThan(givenLength).size();
  }

  @Override
  public List<String> getBookByTitleGivenTitle() throws IOException {
    String title = reader.readLine();

    return this.bookRepository
            .findAllByTitle(title)
            .stream()
            .map(book -> String.format("%s %s %s %.2f",
                    book.getTitle(),
                    book.getEditionType().name(),
                    book.getAgeRestriction().name(),
                    book.getPrice()))
            .collect(Collectors.toList());
  }

  @Override
  public int getCountOfAddedBookCopies() throws IOException {

    String dateInput = reader.readLine();
    int copiesToAdd = Integer.parseInt(reader.readLine());

    LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd MMM yyyy"));

    int booksToUpdate = this.bookRepository.findAllByReleaseDateAfter(date).size();

    this.bookRepository.updateBookCopiesWithReleaseDateAfter(date, copiesToAdd);

    return copiesToAdd * booksToUpdate;
  }

  @Override
  public String deleteBooks() throws IOException {
    int minCopies = Integer.parseInt(reader.readLine());

    return String.format("%d books were removed",
            this.bookRepository.deleteBookByCopiesLessThan(minCopies));
  }

}
