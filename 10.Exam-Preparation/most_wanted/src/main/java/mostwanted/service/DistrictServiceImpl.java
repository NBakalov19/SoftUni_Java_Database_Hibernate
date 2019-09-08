package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static mostwanted.common.Constants.*;

@Service
public class DistrictServiceImpl implements DistrictService {

  private final DistrictRepository districtRepository;
  private final TownRepository townRepository;
  private final FileUtil fileUtil;
  private final ValidationUtil validationUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  @Autowired
  public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository,
                             FileUtil fileUtil, ValidationUtil validationUtil,
                             ModelMapper mapper, Gson gson) {
    this.districtRepository = districtRepository;
    this.townRepository = townRepository;
    this.fileUtil = fileUtil;
    this.validationUtil = validationUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean districtsAreImported() {
    return this.districtRepository.count() > 0;
  }

  @Override
  public String readDistrictsJsonFile() throws IOException {
    return this.fileUtil.readFile(DISTRICT_JSON_FILE_PATH);
  }

  @Override
  public String importDistricts(String districtsFileContent) {

    StringBuilder sb = new StringBuilder();

    DistrictImportDto[] dtos = this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);

    for (DistrictImportDto dto : dtos) {

      District district = this.mapper.map(dto, District.class);

      if (!this.validationUtil.isValid(district)) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      if (this.districtRepository.findByName(district.getName()) != null) {
        sb.append(DUPLICATE_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      Town town = this.townRepository.findByName(dto.getTownName());

      if (town == null) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      district.setTown(town);

      this.districtRepository.saveAndFlush(district);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              district.getClass().getSimpleName(),
              district.getName()))
              .append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}
