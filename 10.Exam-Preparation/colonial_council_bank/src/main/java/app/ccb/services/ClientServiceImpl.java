package app.ccb.services;

import app.ccb.domain.dtos.json.ClientRootDto;
import app.ccb.domain.entities.Card;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.utils.FileUtil;
import app.ccb.utils.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

import static app.ccb.commons.Constant.*;

@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;
  private final EmployeeRepository employeeRepository;
  private final ValidationUtil validationUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  @Autowired
  public ClientServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository,
                           ValidationUtil validationUtil, FileUtil fileUtil,
                           ModelMapper mapper, Gson gson) {
    this.clientRepository = clientRepository;
    this.employeeRepository = employeeRepository;
    this.validationUtil = validationUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean clientsAreImported() {
    return this.clientRepository.count() != 0;
  }

  @Override
  public String readClientsJsonFile() throws IOException {
    return this.fileUtil.readFile(CLIENTS_JSON_FILE_PATH);
  }

  @Override
  @Transactional
  public String importClients(String clients) {

    StringBuilder sb = new StringBuilder();

    ClientRootDto[] dtos = this.gson.fromJson(clients, ClientRootDto[].class);

    for (ClientRootDto dto : dtos) {

      Client client = this.mapper.map(dto, Client.class);

      String fullName = String.format("%s %s", dto.getFirstName(), dto.getLastName());
      client.setFullName(fullName);

      String employeeFirstName = dto.getEmployeeName().split("\\s+")[0];
      String employeeLastName = dto.getEmployeeName().split("\\s+")[1];

      Employee employee = this.employeeRepository
              .findByFirstNameAndLastName(employeeFirstName, employeeLastName);

      if (!this.validationUtil.isValid(client) || employee == null) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      if (this.clientRepository.findByFullName(fullName) != null
              && !client.getEmployees().contains(employee)) {

        client.getEmployees().add(employee);
        employee.getClients().add(client);

        this.employeeRepository.saveAndFlush(employee);

        continue;
      }

      client.getEmployees().add(employee);
      this.clientRepository.saveAndFlush(client);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              client.getClass().getSimpleName(),
              client.getFullName()))
              .append(SEPARATOR);
    }

    return sb.toString().trim();

  }

  @Override
  public String exportFamilyGuy() {
    StringBuilder sb = new StringBuilder();

    Client familyGuy = this.clientRepository.findClientByBankAccount_CountOfCards().get(0);

    sb.append(String.format("Full Name: %s ", familyGuy.getFullName())).append(SEPARATOR);
    sb.append(String.format("Age: %d", familyGuy.getAge())).append(SEPARATOR);
    sb.append(String.format("Bank Account: %s", familyGuy.getBankAccount().getAccountNumber())).append(SEPARATOR);

    for (Card card : familyGuy.getBankAccount().getCards()) {
      sb.append(String.format("  Card Number: %s", card.getCardNumber())).append(SEPARATOR);
      sb.append(String.format("  Card Status: %s", card.getCardStatus())).append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}
