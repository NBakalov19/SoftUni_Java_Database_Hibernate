package mostwanted.service;

import mostwanted.domain.dtos.raceentries.RaceEntryImportDto;
import mostwanted.domain.dtos.raceentries.RaceEntryImportRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static mostwanted.common.Constants.*;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

  private final RaceEntryRepository raceEntryRepository;
  private final RacerRepository racerRepository;
  private final CarRepository carRepository;
  private final FileUtil fileUtil;
  private final ValidationUtil validationUtil;
  private final ModelMapper mapper;
  private final XmlParser xmlParser;

  @Autowired
  public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository,
                              RacerRepository racerRepository, CarRepository carRepository,
                              FileUtil fileUtil, ValidationUtil validationUtil,
                              ModelMapper mapper, XmlParser xmlParser) {
    this.raceEntryRepository = raceEntryRepository;
    this.racerRepository = racerRepository;
    this.carRepository = carRepository;
    this.fileUtil = fileUtil;
    this.validationUtil = validationUtil;
    this.mapper = mapper;
    this.xmlParser = xmlParser;
  }

  @Override
  public Boolean raceEntriesAreImported() {
    return this.raceEntryRepository.count() > 0;
  }

  @Override
  public String readRaceEntriesXmlFile() throws IOException {
    return this.fileUtil.readFile(RACE_ENTRIES_XML_FILE_PATH);
  }

  @Override
  public String importRaceEntries() throws JAXBException, FileNotFoundException {

    StringBuilder sb = new StringBuilder();

    RaceEntryImportRootDto raceEntryImportRootDto =
            this.xmlParser.parseXml(RaceEntryImportRootDto.class, RACE_ENTRIES_XML_FILE_PATH);

    int id = 0;

    for (RaceEntryImportDto raceEntryImportDto : raceEntryImportRootDto.getRaceEntryImportDtos()) {

      RaceEntry raceEntry = this.mapper.map(raceEntryImportDto, RaceEntry.class);
      Racer racer = this.racerRepository.findByName(raceEntryImportDto.getRacer());
      Car car = this.carRepository.findById(raceEntryImportDto.getCarId()).orElse(null);

      if (!this.validationUtil.isValid(raceEntry) || racer == null || car == null) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      raceEntry.setRace(null);
      raceEntry.setCar(car);
      raceEntry.setRacer(racer);

      this.raceEntryRepository.saveAndFlush(raceEntry);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              raceEntry.getClass().getSimpleName(),
              ++id))
              .append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}
