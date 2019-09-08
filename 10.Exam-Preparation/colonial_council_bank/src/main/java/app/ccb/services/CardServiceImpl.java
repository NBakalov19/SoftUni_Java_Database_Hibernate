package app.ccb.services;

import app.ccb.domain.dtos.xml.cardsXml.CardImportDto;
import app.ccb.domain.dtos.xml.cardsXml.CardImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;
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
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;
  private final BankAccountRepository bankAccountRepository;
  private final ValidationUtil validationUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final XmlParser xmlParser;

  @Autowired
  public CardServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository,
                         ValidationUtil validationUtil, FileUtil fileUtil,
                         ModelMapper mapper, XmlParser xmlParser) {
    this.cardRepository = cardRepository;
    this.bankAccountRepository = bankAccountRepository;
    this.validationUtil = validationUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.xmlParser = xmlParser;
  }

  @Override
  public Boolean cardsAreImported() {
    return this.cardRepository.count() != 0;
  }

  @Override
  public String readCardsXmlFile() throws IOException {
    return this.fileUtil.readFile(CARDS_XML_FILE_PATH);
  }

  @Override
  public String importCards() throws JAXBException, FileNotFoundException {
    StringBuilder sb = new StringBuilder();

    CardImportRootDto dtos = this.xmlParser.parseXml(
            CardImportRootDto.class, CARDS_XML_FILE_PATH);

    for (CardImportDto cardImportDto : dtos.getCardImportDtos()) {

      Card card = this.mapper.map(cardImportDto, Card.class);

      BankAccount bankAccount = this.bankAccountRepository
              .findByAccountNumber(cardImportDto.getAccountNumber())
              .orElse(null);

      if (!this.validationUtil.isValid(card) || bankAccount == null) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);
      }

      card.setBankAccount(bankAccount);

      this.cardRepository.saveAndFlush(card);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              card.getClass().getSimpleName(),
              card.getCardNumber()))
              .append(SEPARATOR);
    }


    return sb.toString().trim();
  }
}
