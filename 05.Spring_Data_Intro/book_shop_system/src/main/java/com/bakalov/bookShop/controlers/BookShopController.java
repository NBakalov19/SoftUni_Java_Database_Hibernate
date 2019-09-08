package com.bakalov.bookShop.controlers;

import com.bakalov.bookShop.services.AuthorService;
import com.bakalov.bookShop.services.BookService;
import com.bakalov.bookShop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookShopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BookShopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.authorService.seedAuthors();
//        this.categoryService.seedCategory();
//        this.bookService.seedBooks();
//        this.findAllBookAfterYear();
//        this.findAllAuthorsWithBookBefore();
//        this.getAuthorsOrderByCountOfBooks();
//        this.getGeorgePowellBooks();
    }

    private void findAllBookAfterYear() {
        List<String> titles = this.bookService.findAllTitles();
        titles.forEach(System.out::println);
    }

    private void findAllAuthorsWithBookBefore() {
        List<String> authors = this.bookService.findAllAuthors();
        authors.forEach(System.out::println);
    }

    private void getAuthorsOrderByCountOfBooks() {
        List<String> authors = this.authorService.getAllAuthorsByBookCount();
        authors.forEach(System.out::println);
    }

    private void getGeorgePowellBooks() {
        this.bookService.findGeorgePowellBooks()
                .forEach(System.out::println);
    }
}
