package alararestaurant.service;

import alararestaurant.domain.dtos.json.EmployeeSeedDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private static final String EMPLOYEE_JSON_IMPORT_FILE_PATH =
          "C:\\Users\\Nikolay\\Projects\\Hibernate\\11.Exam-Preparation\\Demonstration"
                  + "\\alara_restaurant\\src\\main\\resources\\files\\employees.json";

  private final EmployeeRepository employeeRepository;
  private final PositionRepository positionRepository;
  private final ValidationUtil validator;
  private final FileUtil fileUtil;
  private final ModelMapper modelMapper;
  private final Gson gson;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository,
                             ValidationUtil validator, FileUtil fileUtil,
                             ModelMapper modelMapper, Gson gson) {
    this.employeeRepository = employeeRepository;
    this.positionRepository = positionRepository;
    this.validator = validator;
    this.fileUtil = fileUtil;
    this.modelMapper = modelMapper;
    this.gson = gson;
  }

  @Override
  public Boolean employeesAreImported() {
    return this.employeeRepository.count() > 0;
  }

  @Override
  public String readEmployeesJsonFile() throws IOException {

    return this.fileUtil.readFile(EMPLOYEE_JSON_IMPORT_FILE_PATH);
  }

  @Override
  public String importEmployees(String employees) {

    StringBuilder builder = new StringBuilder();
    String separator = System.lineSeparator();

    EmployeeSeedDto[] dtos = this.gson.fromJson(employees, EmployeeSeedDto[].class);

    for (EmployeeSeedDto dto : dtos) {

      Employee employee = this.modelMapper.map(dto, Employee.class);

      if (!this.validator.isValid(employee)) {
        builder.append("Invalid data format.").append(separator);

        continue;
      }

      Position position = this.positionRepository.findByName(dto.getPosition());

      if (position == null) {
        position = new Position();
        position.setName(dto.getPosition());

        if (!this.validator.isValid(position)) {
          builder.append("Invalid data format.").append(separator);
          continue;
        }

        position = this.positionRepository.saveAndFlush(position);
      }

      employee.setPosition(position);
      this.employeeRepository.saveAndFlush(employee);

      builder.append(String.format("Record %s successfully imported.", employee.getName()))
              .append(separator);

    }

    return builder.toString().trim();
  }
}
