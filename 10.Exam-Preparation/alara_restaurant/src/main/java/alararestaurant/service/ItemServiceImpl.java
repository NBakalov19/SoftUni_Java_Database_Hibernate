package alararestaurant.service;

import alararestaurant.domain.dtos.json.ItemSeedDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {

  private static final String ITEMS_INPUT_JSON_FILE_PATH =
          "C:\\Users\\Nikolay\\Projects\\Hibernate\\11.Exam-Preparation\\Demonstration"
                  + "\\alara_restaurant\\src\\main\\resources\\files\\items.json";

  private final ItemRepository itemRepository;
  private final CategoryRepository categoryRepository;
  private final ValidationUtil validator;
  private final FileUtil fileUtil;
  private final ModelMapper modelMapper;
  private final Gson gson;

  public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, ValidationUtil validator,
                         FileUtil fileUtil, ModelMapper modelMapper,
                         Gson gson) {
    this.itemRepository = itemRepository;
    this.categoryRepository = categoryRepository;
    this.validator = validator;
    this.fileUtil = fileUtil;
    this.modelMapper = modelMapper;
    this.gson = gson;
  }

  @Override
  public Boolean itemsAreImported() {
    return this.itemRepository.count() > 0;
  }

  @Override
  public String readItemsJsonFile() throws IOException {
    return this.fileUtil.readFile(ITEMS_INPUT_JSON_FILE_PATH);
  }

  @Override
  public String importItems(String items) {

    StringBuilder builder = new StringBuilder();
    String separator = System.lineSeparator();

    ItemSeedDto[] dtos = this.gson.fromJson(items, ItemSeedDto[].class);


    for (ItemSeedDto dto : dtos) {

      Item item = this.modelMapper.map(dto, Item.class);

      if (!this.validator.isValid(item) || this.itemRepository.findByName(item.getName()) != null) {
        builder.append("Invalid data format.").append(separator);

        continue;
      }

      Category category = this.categoryRepository.findByName(dto.getCategory());

      if (category == null) {
        category = new Category();
        category.setName(dto.getCategory());

        if (!this.validator.isValid(category)) {
          builder.append("Invalid data format.").append(separator);

          continue;
        }

        category = this.categoryRepository.saveAndFlush(category);
      }

      item.setCategory(category);
      this.itemRepository.saveAndFlush(item);

      builder.append(String.format("Record %s successfully imported.", item.getName())).append(separator);
    }

    return builder.toString().trim();
  }
}
