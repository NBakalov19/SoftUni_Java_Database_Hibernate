package mostwanted.service;

import mostwanted.domain.dtos.races.EntryImportDto;
import mostwanted.domain.dtos.races.RaceImportDto;
import mostwanted.domain.dtos.races.RaceImportRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static mostwanted.common.Constants.*;

@Service
public class RaceServiceImpl implements RaceService {

  private final RaceRepository raceRepository;
  private final DistrictRepository districtRepository;
  private final RaceEntryRepository raceEntryRepository;
  private final FileUtil fileUtil;
  private final ValidationUtil validationUtil;
  private final ModelMapper mapper;
  private final XmlParser xmlParser;

  @Autowired
  public RaceServiceImpl(RaceRepository raceRepository, DistrictRepository districtRepository, RaceEntryRepository raceEntryRepository, FileUtil fileUtil,
                         ValidationUtil validationUtil, ModelMapper mapper,
                         XmlParser xmlParser) {
    this.raceRepository = raceRepository;
    this.districtRepository = districtRepository;
    this.raceEntryRepository = raceEntryRepository;
    this.fileUtil = fileUtil;
    this.validationUtil = validationUtil;
    this.mapper = mapper;
    this.xmlParser = xmlParser;
  }

  @Override
  public Boolean racesAreImported() {
    return this.raceRepository.count() > 0;
  }

  @Override
  public String readRacesXmlFile() throws IOException {
    return this.fileUtil.readFile(RACES_XML_FILE_PATH);
  }

  @Override
  public String importRaces() throws JAXBException, FileNotFoundException {
    StringBuilder sb = new StringBuilder();

    RaceImportRootDto raceImportRootDto =
            this.xmlParser.parseXml(RaceImportRootDto.class, RACES_XML_FILE_PATH);


    int id = 0;

    RaceImportLoop:
    for (RaceImportDto raceImportDto : raceImportRootDto.getRaceList()) {

      Race race = this.mapper.map(raceImportDto, Race.class);
      District district = this.districtRepository.findByName(raceImportDto.getDistrictName());

      if (!this.validationUtil.isValid(race) || district == null) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      List<RaceEntry> raceEntries = new ArrayList<>();

      for (EntryImportDto entry : raceImportDto.getEntries().getEntries()) {

        RaceEntry raceEntry = this.raceEntryRepository.findById(entry.getId()).orElse(null);

        if (raceEntry == null) {
          continue RaceImportLoop;
        }

        raceEntry.setRace(race);
        raceEntries.add(raceEntry);
      }

      if (this.raceRepository.findByDistrictAndLaps(district, race.getLaps()) != null) {

        sb.append(DUPLICATE_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      race.setDistrict(district);
      race.setEntries(raceEntries);

      this.raceRepository.saveAndFlush(race);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              race.getClass().getSimpleName(),
              ++id))
              .append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}