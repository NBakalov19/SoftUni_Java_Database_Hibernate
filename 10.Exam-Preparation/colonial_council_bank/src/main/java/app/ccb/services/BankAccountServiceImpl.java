package app.ccb.services;

import app.ccb.domain.dtos.xml.bankAccountXml.BankAccountImportDto;
import app.ccb.domain.dtos.xml.bankAccountXml.BankAccountImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.utils.FileUtil;
import app.ccb.utils.ValidationUtil;
import app.ccb.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static app.ccb.commons.Constant.*;

@Service
public class BankAccountServiceImpl implements BankAccountService {

  private final BankAccountRepository bankAccountRepository;
  private final ClientRepository clientRepository;
  private final ValidationUtil validationUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final XmlParser xmlParser;

  @Autowired
  public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository,
                                ValidationUtil validationUtil, FileUtil fileUtil,
                                ModelMapper mapper, XmlParser xmlParser) {
    this.bankAccountRepository = bankAccountRepository;
    this.clientRepository = clientRepository;
    this.validationUtil = validationUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.xmlParser = xmlParser;
  }

  @Override
  public Boolean bankAccountsAreImported() {
    return this.bankAccountRepository.count() != 0;
  }

  @Override
  public String readBankAccountsXmlFile() throws IOException {
    return this.fileUtil.readFile(BANK_ACCOUNTS_XML_FILE_PATH);
  }

  @Override
  public String importBankAccounts() throws JAXBException, FileNotFoundException {
    StringBuilder sb = new StringBuilder();

    BankAccountImportRootDto dtos =
            this.xmlParser.parseXml(BankAccountImportRootDto.class, BANK_ACCOUNTS_XML_FILE_PATH);

    for (BankAccountImportDto dto : dtos.getBankAccountImportDtos()) {

      BankAccount bankAccount = this.mapper.map(dto, BankAccount.class);

      Client client = this.clientRepository.findByFullName(dto.getClientName());

      if (!this.validationUtil.isValid(bankAccount) || client == null) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      bankAccount.setClient(client);

      this.bankAccountRepository.saveAndFlush(bankAccount);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              bankAccount.getClass().getSimpleName(),
              bankAccount.getAccountNumber()))
              .append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}
