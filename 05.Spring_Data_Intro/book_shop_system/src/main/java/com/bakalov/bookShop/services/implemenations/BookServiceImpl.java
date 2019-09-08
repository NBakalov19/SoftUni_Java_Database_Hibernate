package com.bakalov.bookShop.services.implemenations;

import com.bakalov.bookShop.entities.*;
import com.bakalov.bookShop.repositories.AuthorRepository;
import com.bakalov.bookShop.repositories.BookRepository;
import com.bakalov.bookShop.repositories.CategoryRepository;
import com.bakalov.bookShop.services.BookService;
import com.bakalov.bookShop.utilities.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH =
            "C:\\Users\\Nikolay\\Projects\\Hibernate\\05.SpringDataIntro\\" +
                    "Exercise\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileReader reader;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileReader reader) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.reader = reader;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] books = this.reader.getFileContent(BOOKS_FILE_PATH);

        for (String bookInfo : books) {
            String[] params = bookInfo.split("\\s+");

            Book book = new Book();
            book.setAuthor(this.randomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(params[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(params[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(params[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder titleBuilder = new StringBuilder();

            for (int i = 5; i < params.length; i++) {
                titleBuilder.append(params[i]).append(" ");
            }

            book.setTitle(titleBuilder.toString().trim());

            book.setCategories(this.getRandomCategories());

            this.bookRepository.saveAndFlush(book);
        }
    }

    private Author randomAuthor() {
        Random random = new Random();

        int index = random.nextInt((int) this.authorRepository.count()) + 1;

        Author author =  this.authorRepository.getOne(index);

        return this.authorRepository.getOne(index);
    }

    private Category randomCategory() {
        Random random = new Random();

        int index = random.nextInt((int) this.categoryRepository.count()) + 1;

        return this.categoryRepository.getOne(index);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        Random random = new Random();

        int index = random.nextInt((int) this.categoryRepository.count()) + 1;

        for (int i = 0; i < index; i++) {
            categories.add(randomCategory());
        }
        return categories;
    }

    @Override
    public List<String> findAllTitles() {

        LocalDate releaseDate = LocalDate.parse("31/12/2000", DateTimeFormatter.ofPattern("d/M/yyyy"));

        return this.bookRepository
                .findAllByReleaseDateAfter(releaseDate)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllAuthors() {
        LocalDate releaseDate = LocalDate.parse("01/01/1990", DateTimeFormatter.ofPattern("d/M/yyyy"));


        return this.bookRepository
                .findAllByReleaseDateBefore(releaseDate)
                .stream()
                .map(book -> String.format("%s %s",
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findGeorgePowellBooks() {

        String firstName = "George";
        String lastName = "Powell";

        return this.bookRepository
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(
                        firstName,lastName)
                .stream()
                .map(book -> String.format("Title: %s release on %s and have %d copies",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }
}