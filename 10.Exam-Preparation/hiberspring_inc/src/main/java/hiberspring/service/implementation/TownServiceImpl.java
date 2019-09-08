package hiberspring.service.implementation;

import com.google.gson.Gson;
import hiberspring.domain.dtos.json.TownImportDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class TownServiceImpl implements TownService {

  private final TownRepository townRepository;
  private final ValidationUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  public TownServiceImpl(TownRepository townRepository,
                         ValidationUtil validatorUtil, FileUtil fileUtil,
                         ModelMapper mapper, Gson gson) {
    this.townRepository = townRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean townsAreImported() {
    return this.townRepository.count() > 0;
  }

  @Override
  public String readTownsJsonFile() throws IOException {
    return this.fileUtil.readFile(PATH_TO_FILES + "towns.json");
  }

  @Override
  public String importTowns(String townsFileContent) {
    StringBuilder sb = new StringBuilder();

    TownImportDto[] townImportDtos = this.gson.fromJson(townsFileContent, TownImportDto[].class);

    for (TownImportDto townDto : townImportDtos) {

      Town town = this.mapper.map(townDto, Town.class);

      if (!this.validatorUtil.isValid(town)) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);
        continue;
      }

      this.townRepository.saveAndFlush(town);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              town.getClass().getSimpleName(),
              town.getName())).append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}
