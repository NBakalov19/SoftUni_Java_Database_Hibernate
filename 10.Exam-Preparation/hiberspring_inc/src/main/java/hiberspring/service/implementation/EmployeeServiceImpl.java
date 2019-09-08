package hiberspring.service.implementation;

import hiberspring.domain.dtos.xml.EmployeeImportDto;
import hiberspring.domain.dtos.xml.EmployeeImportRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeCardRepository employeeCardRepository;
  private final BranchRepository branchRepository;
  private final ValidationUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final XmlParser xmlParser;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                             EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository,
                             ValidationUtil validatorUtil, FileUtil fileUtil,
                             ModelMapper mapper, XmlParser xmlParser) {
    this.employeeRepository = employeeRepository;
    this.employeeCardRepository = employeeCardRepository;
    this.branchRepository = branchRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.xmlParser = xmlParser;
  }

  @Override
  public Boolean employeesAreImported() {
    return this.employeeRepository.count() > 0;
  }

  @Override
  public String readEmployeesXmlFile() throws IOException {
    return this.fileUtil.readFile(PATH_TO_FILES + "employees.xml");
  }

  @Override
  public String importEmployees() throws JAXBException, FileNotFoundException {
    StringBuilder sb = new StringBuilder();

    EmployeeImportRootDto employeeImportRootDto =
            this.xmlParser.parseXml(EmployeeImportRootDto.class, PATH_TO_FILES + "employees.xml");

    for (EmployeeImportDto employeeImportDto : employeeImportRootDto.getEmployeeImportDtos()) {

      Employee employee = this.mapper.map(employeeImportDto, Employee.class);

      EmployeeCard employeeCard = this.employeeCardRepository.findByNumber(employeeImportDto.getCardNumber());

      Branch branch = this.branchRepository.findByName(employeeImportDto.getBranchName());

      employee.setCard(employeeCard);
      employee.setBranch(branch);

      if (!this.validatorUtil.isValid(employee)) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      String employeeFullName = String.format("%s %s", employee.getFirstName(), employee.getLastName());

      this.employeeRepository.saveAndFlush(employee);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              employee.getClass().getSimpleName(),
              employeeFullName))
              .append(SEPARATOR);
    }

    return sb.toString().trim();
  }

  @Override
  public String exportProductiveEmployees() {
    return null;
  }
}
