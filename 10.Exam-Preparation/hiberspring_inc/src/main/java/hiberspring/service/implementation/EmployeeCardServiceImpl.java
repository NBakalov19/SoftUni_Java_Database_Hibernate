package hiberspring.service.implementation;

import com.google.gson.Gson;
import hiberspring.domain.dtos.json.EmployeeCardImportDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {

  private final EmployeeCardRepository employeeCardRepository;
  private final ValidationUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  @Autowired
  public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository,
                                 ValidationUtil validatorUtil, FileUtil fileUtil,
                                 ModelMapper mapper, Gson gson) {
    this.employeeCardRepository = employeeCardRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean employeeCardsAreImported() {
    return this.employeeCardRepository.count() > 0;
  }

  @Override
  public String readEmployeeCardsJsonFile() throws IOException {
    return this.fileUtil.readFile(PATH_TO_FILES + "employee-cards.json");
  }

  @Override
  public String importEmployeeCards(String employeeCardsFileContent) {
    StringBuilder sb = new StringBuilder();

    EmployeeCardImportDto[] employeeCardImportDtos =
            this.gson.fromJson(employeeCardsFileContent, EmployeeCardImportDto[].class);

    for (EmployeeCardImportDto employeeCardImportDto : employeeCardImportDtos) {

      EmployeeCard employeeCard = this.mapper.map(employeeCardImportDto, EmployeeCard.class);

      EmployeeCard isExist = this.employeeCardRepository.findByNumber(employeeCardImportDto.getNumber());

      if (!this.validatorUtil.isValid(employeeCard) || isExist != null) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      this.employeeCardRepository.saveAndFlush(employeeCard);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              employeeCard.getClass().getSimpleName(),
              employeeCard.getNumber()))
              .append(SEPARATOR);

    }

    return sb.toString().trim();
  }
}
