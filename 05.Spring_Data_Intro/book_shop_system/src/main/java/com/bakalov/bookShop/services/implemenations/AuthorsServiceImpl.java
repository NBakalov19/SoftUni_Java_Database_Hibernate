package com.bakalov.bookShop.services.implemenations;

import com.bakalov.bookShop.entities.Author;
import com.bakalov.bookShop.repositories.AuthorRepository;
import com.bakalov.bookShop.services.AuthorService;
import com.bakalov.bookShop.utilities.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorsServiceImpl implements AuthorService {

    private static final String AUTHOR_FILE_PATH =
            "C:\\Users\\Nikolay\\Projects\\Hibernate\\05.SpringDataIntro\\" +
                    "Exercise\\src\\main\\resources\\files\\authors.txt";

    private final AuthorRepository authorRepository;
    private final FileReader fileReader;

    @Autowired
    public AuthorsServiceImpl(AuthorRepository authorRepository, FileReader fileReader) {
        this.authorRepository = authorRepository;
        this.fileReader = fileReader;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] authors = fileReader.getFileContent(AUTHOR_FILE_PATH);

        for (String authorInfo : authors) {
            Author author = new Author();
            String firstName = authorInfo.split("\\s+")[0];
            String lastName = authorInfo.split("\\s+")[1];

            author.setFirstName(firstName);
            author.setLastName(lastName);

            this.authorRepository.saveAndFlush(author);
        }

    }

    @Override
    public List<String> getAllAuthorsByBookCount() {

        return this.authorRepository
                .findAll()
                .stream()
                .sorted(
                        (a1,a2)-> Integer.compare(a2.getBooks().size(), a1.getBooks().size()))
                .map(author -> String.format("%s %s - %d books",
                        author.getFirstName(),author.getLastName(),author.getBooks().size()))
                .collect(Collectors.toList());
    }
}
