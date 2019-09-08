package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeImportRootDto implements Serializable {

  @XmlElement(name = "employee")
  private List<EmployeeImportDto> employeeImportDtos;

  public EmployeeImportRootDto() {
    this.employeeImportDtos = new ArrayList<>();
  }

  public List<EmployeeImportDto> getEmployeeImportDtos() {
    return employeeImportDtos;
  }

  public void setEmployeeImportDtos(List<EmployeeImportDto> employeeImportDtos) {
    this.employeeImportDtos = employeeImportDtos;
  }
}
