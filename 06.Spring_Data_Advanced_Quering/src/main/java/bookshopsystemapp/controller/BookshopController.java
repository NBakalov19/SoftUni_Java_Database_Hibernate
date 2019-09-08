package bookshopsystemapp.controller;

import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;

@Controller
public class BookshopController implements CommandLineRunner {

  private final BufferedReader reader;

  private final AuthorService authorService;
  private final CategoryService categoryService;
  private final BookService bookService;

  @Autowired
  public BookshopController(BufferedReader reader, AuthorService authorService,
                            CategoryService categoryService, BookService bookService) {
    this.reader = reader;
    this.authorService = authorService;
    this.categoryService = categoryService;
    this.bookService = bookService;
  }

  @Override
  public void run(String... strings) throws Exception {
//        this.authorService.seedAuthors();
//        this.categoryService.seedCategories();
//        this.bookService.seedBooks();

    while (true) {
      System.out.println("Please enter task number to execute or enter finish to stop:");
      String task = reader.readLine().toLowerCase();

      switch (task) {
        case "1":
          this.printBooksTitleByAgeRestriction();
          break;
        case "2":
          this.printGoldenBooks();
          break;
        case "3":
          this.printBooksByPrice();
        case "4":
          this.printBooksNotInGivenYear();
          break;
        case "5":
          this.printBooksReleasedBefore();
          break;
        case "6":
          this.printAuthorsFirstNameEndsWithGivenPattern();
          break;
        case "7":
          this.printAllTitlesWithMatchingPattern();
          break;
        case "8":
          this.printAllBooksFromAuthorWithLastNameStartsWithGivenPattern();
          break;
        case "9":
          this.countOfBookTitleLengthLongerByGiven();
          break;
        case "10":
          this.getAuthorsByCopies();
          break;
        case "11":
          this.getBookByTitle();
          break;
        case "12":
          this.getIncreasedBookCopies();
          break;
        case "13":
          this.deleteBooksLessThanGivenCountOfCopies();
          break;
        case "finish":
          System.exit(0);
          break;
        default:
          System.out.println("Enter valid task number or enter finish to stop executing");
          break;
      }
    }
  }

  //1. Books Titles by Age Restriction
  private void printBooksTitleByAgeRestriction() throws IOException {

    this.bookService
            .getAllBookTitleByAgeRestriction()
            .forEach(System.out::println);
  }

  //2.Golden Books
  private void printGoldenBooks() {

    this.bookService
            .getAllGoldenBookEditions()
            .forEach(System.out::println);
  }

  //3. Books by Price
  private void printBooksByPrice() {

    this.bookService
            .getAllBooksWithPriceBetween()
            .forEach(System.out::println);
  }

  //4. Not Released Books
  private void printBooksNotInGivenYear() throws IOException {

    this.bookService.getAllBooksInNotGivenYear()
            .forEach(System.out::println);
  }

  //5. Books Released Before Date
  private void printBooksReleasedBefore() throws IOException {

    this.bookService.getAllBooksWithReleaseDateBefore()
            .forEach(System.out::println);
  }

  //6. Authors Search
  private void printAuthorsFirstNameEndsWithGivenPattern() throws IOException {

    this.authorService.getAuthorsWithFirstNameEndsWith()
            .forEach(System.out::println);
  }

  //7. Books Search
  private void printAllTitlesWithMatchingPattern() throws IOException {

    this.bookService.getAllTitlesContainsPattern()
            .forEach(System.out::println);
  }

  //8. Book Titles Search
  private void printAllBooksFromAuthorWithLastNameStartsWithGivenPattern() throws IOException {

    this.bookService.getAllBooksFromAuthorWhereLastNameStarts()
            .forEach(System.out::println);
  }

  //9. Count Books
  private void countOfBookTitleLengthLongerByGiven() throws IOException {

    System.out.println(this.bookService.countOfBookTitleLengthLongerByGivenLength());
  }

  //10. Total Book Copies
  private void getAuthorsByCopies() {

    this.authorService.getAuthorsWithCopies()
            .forEach(System.out::println);
  }

  //11. Reduced Book
  private void getBookByTitle() throws IOException {

    this.bookService.getBookByTitleGivenTitle()
            .forEach(System.out::println);
  }

  //12. Increase Book Copies
  private void getIncreasedBookCopies() throws IOException {

    System.out.println(this.bookService.getCountOfAddedBookCopies());
  }

  //13. Remove Books
  private void deleteBooksLessThanGivenCountOfCopies() throws IOException {

    System.out.println(this.bookService.deleteBooks());
  }
}