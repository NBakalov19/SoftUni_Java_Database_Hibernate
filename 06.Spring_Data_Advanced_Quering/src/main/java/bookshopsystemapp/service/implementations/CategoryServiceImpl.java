package bookshopsystemapp.service.implementations;

import bookshopsystemapp.domain.entities.Category;
import bookshopsystemapp.repository.CategoryRepository;
import bookshopsystemapp.service.CategoryService;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static bookshopsystemapp.util.Constants.CATEGORIES_FILE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final FileUtil fileUtil;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
    this.categoryRepository = categoryRepository;
    this.fileUtil = fileUtil;
  }

  @Override
  public void seedCategories() throws IOException {
    if (this.categoryRepository.count() != 0) {
      return;
    }

    String[] categoriesFileContent = this.fileUtil.getFileContent(CATEGORIES_FILE_PATH);
    for (String line : categoriesFileContent) {
      Category category = new Category();
      category.setName(line);

      this.categoryRepository.saveAndFlush(category);
    }
  }
}
