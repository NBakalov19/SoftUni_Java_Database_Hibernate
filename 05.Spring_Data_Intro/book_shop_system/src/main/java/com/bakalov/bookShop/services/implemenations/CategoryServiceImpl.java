package com.bakalov.bookShop.services.implemenations;

import com.bakalov.bookShop.entities.Category;
import com.bakalov.bookShop.repositories.CategoryRepository;
import com.bakalov.bookShop.services.CategoryService;
import com.bakalov.bookShop.utilities.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_FILE_PATH =
            "C:\\Users\\Nikolay\\Projects\\Hibernate\\05.SpringDataIntro\\" +
                    "Exercise\\src\\main\\resources\\files\\categories.txt";

    private final CategoryRepository categoryRepository;
    private final FileReader fileReader;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileReader fileReader) {
        this.categoryRepository = categoryRepository;
        this.fileReader = fileReader;
    }

    @Override
    public void seedCategory() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }

        String[] categories = this.fileReader.getFileContent(CATEGORY_FILE_PATH);

        for (String categoryInfo : categories) {
            Category category = new Category();
            category.setName(categoryInfo);

            this.categoryRepository.saveAndFlush(category);
        }

    }
}
