package app.ccb.services;

import app.ccb.domain.dtos.json.EmployeeImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.utils.FileUtil;
import app.ccb.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static app.ccb.commons.Constant.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final BranchRepository branchRepository;
  private final ValidationUtil validationUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository,
                             ValidationUtil validationUtil, FileUtil fileUtil,
                             ModelMapper mapper, Gson gson) {
    this.employeeRepository = employeeRepository;
    this.branchRepository = branchRepository;
    this.validationUtil = validationUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean employeesAreImported() {
    return this.employeeRepository.count() != 0;
  }

  @Override
  public String readEmployeesJsonFile() throws IOException {
    return this.fileUtil.readFile(EMPLOYEES_JSON_FILE_PATH);
  }

  @Override
  public String importEmployees(String employees) {
    StringBuilder sb = new StringBuilder();

    EmployeeImportDto[] dtos = this.gson.fromJson(employees, EmployeeImportDto[].class);

    for (EmployeeImportDto dto : dtos) {

      Employee employee = this.mapper.map(dto, Employee.class);

      String firstName = dto.getFullName().split("\\s+")[0];
      String lastName = dto.getFullName().split("\\s+")[1];

      Branch branch = this.branchRepository.findByName(dto.getBranchName());

      employee.setFirstName(firstName);
      employee.setLastName(lastName);
      employee.setStartedOn(LocalDate.parse(dto.getStartedOn(), DateTimeFormatter.ISO_LOCAL_DATE));
      employee.setBranch(branch);

      if (!this.validationUtil.isValid(employee)) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);
        continue;
      }

      this.employeeRepository.saveAndFlush(employee);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              employee.getClass().getSimpleName(),
              dto.getFullName()))
              .append(SEPARATOR);
    }

    return sb.toString().trim();
  }

  @Override
  public String exportTopEmployees() {
    StringBuilder sb = new StringBuilder();

    List<Employee> employees = this.employeeRepository.findAllByClientsIsNotNull();

    for (Employee employee : employees) {
      sb.append(String.format("Full Name: %s %s",
              employee.getFirstName(),
              employee.getLastName())).append(SEPARATOR);

      sb.append(String.format("Salary: %.2f", employee.getSalary())).append(SEPARATOR);
      sb.append("Clients:").append(SEPARATOR);

      for (Client client : employee.getClients()) {
        sb.append(String.format("  %s", client.getFullName())).append(SEPARATOR);
      }
      sb.append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}
