package bookshopsystemapp.service.implementations;

import bookshopsystemapp.domain.entities.Author;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static bookshopsystemapp.util.Constants.AUTHORS_FILE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {


  private final AuthorRepository authorRepository;
  private final FileUtil fileUtil;
  private final BufferedReader reader;

  @Autowired
  public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil, BufferedReader reader) {
    this.authorRepository = authorRepository;
    this.fileUtil = fileUtil;
    this.reader = reader;
  }

  @Override
  public void seedAuthors() throws IOException {
    if (this.authorRepository.count() != 0) {
      return;
    }

    String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_FILE_PATH);
    for (String line : authorFileContent) {
      String[] names = line.split("\\s+");

      Author author = new Author();
      author.setFirstName(names[0]);
      author.setLastName(names[1]);

      this.authorRepository.saveAndFlush(author);
    }
  }

  @Override
  public List<String> getAuthorsWithFirstNameEndsWith() throws IOException {

    String pattern = reader.readLine();

    return this.authorRepository.findAuthorsByFirstNameEndsWith(pattern)
            .stream()
            .map(author -> String.format("%s %s",
                    author.getFirstName(),
                    author.getLastName()))
            .collect(Collectors.toList());
  }

  @Override
  public List<String> getAuthorsWithCopies() {

    return this.authorRepository
            .getAllByCopies()
            .stream()
            .map(obj -> (String) obj)
            .collect(Collectors.toList());
  }
}
