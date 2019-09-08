package bookshopsystemapp.util;

import java.math.BigDecimal;

public final class Constants {

  public final static String BOOKS_FILE_PATH = "E:\\SoftUni\\Software_Engineering\\Java\\Java-Database\\" +
          "Database-Hibernate\\06.Spring-Data-Advanced-Quering\\Exercise\\Skeleton-Exercise\\src\\main\\resources" +
          "\\files\\books.txt";

  public final static String AUTHORS_FILE_PATH = "E:\\SoftUni\\Software_Engineering\\Java\\Java-Database\\" +
          "Database-Hibernate\\06.Spring-Data-Advanced-Quering\\Exercise\\Skeleton-Exercise\\src\\main\\resources" +
          "\\files\\authors.txt";

  public final static String CATEGORIES_FILE_PATH = "E:\\SoftUni\\Software_Engineering\\Java\\Java-Database" +
          "\\Database-Hibernate\\06.Spring-Data-Advanced-Quering\\Exercise\\Skeleton-Exercise\\src\\main\\resources" +
          "\\files\\categories.txt";


  public static final Integer GOLDEN_EDITION_BOOK_COPIES_VALUE = 5000;
  public static final BigDecimal MIN_PRICE = BigDecimal.valueOf(5L);
  public static final BigDecimal MAX_PRICE = BigDecimal.valueOf(40L);


  public Constants() {
  }
}
