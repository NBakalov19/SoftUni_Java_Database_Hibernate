package hiberspring.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface EmployeeService {

  Boolean employeesAreImported();

  String readEmployeesXmlFile() throws IOException;

  String importEmployees() throws JAXBException, FileNotFoundException;

  String exportProductiveEmployees();
}
