package com.bakalov.bookShop.services;

import java.io.IOException;
import java.util.List;


public interface AuthorService {
    void seedAuthors() throws IOException;

    List<String> getAllAuthorsByBookCount();
}
