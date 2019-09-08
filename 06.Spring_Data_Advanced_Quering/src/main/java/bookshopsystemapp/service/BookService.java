package bookshopsystemapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BookService {

  void seedBooks() throws IOException;

  List<String> getAllBooksTitlesAfter();

  Set<String> getAllAuthorsWithBookBefore();

  List<String> getAllBookTitleByAgeRestriction() throws IOException;

  List<String> getAllGoldenBookEditions();

  List<String> getAllBooksWithPriceBetween();

  List<String> getAllBooksInNotGivenYear() throws IOException;

  List<String> getAllBooksWithReleaseDateBefore() throws IOException;

  List<String> getAllTitlesContainsPattern() throws IOException;

  List<String> getAllBooksFromAuthorWhereLastNameStarts() throws IOException;

  Integer countOfBookTitleLengthLongerByGivenLength() throws IOException;

  List<String> getBookByTitleGivenTitle() throws IOException;

  int getCountOfAddedBookCopies() throws IOException;

  String deleteBooks() throws IOException;

//    void test() throws IOException;
}
