package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.xml.TeamImportDto;
import softuni.exam.domain.dtos.xml.TeamImportRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParserUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static softuni.exam.common.Constants.*;

@Service
public class TeamServiceImpl implements TeamService {

  private final TeamRepository teamRepository;
  private final PictureRepository pictureRepository;
  private final ValidatorUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final XmlParserUtil xmlParserUtil;

  @Autowired
  public TeamServiceImpl(TeamRepository teamRepository, PictureRepository pictureRepository,
                         ValidatorUtil validatorUtil, FileUtil fileUtil,
                         ModelMapper mapper, XmlParserUtil xmlParserUtil) {
    this.teamRepository = teamRepository;
    this.pictureRepository = pictureRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.xmlParserUtil = xmlParserUtil;
  }

  @Override
  public String importTeams() throws IOException, JAXBException {
    StringBuilder sb = new StringBuilder();

    TeamImportRootDto teamImportRootDtos =
            this.xmlParserUtil.parseXml(TeamImportRootDto.class, TEAMS_FILE_INPUT_PATH);

    for (TeamImportDto teamImportDto : teamImportRootDtos.getTeamImportDtos()) {

      if (!this.validatorUtil.isValid(teamImportDto)) {
        this.validatorUtil.violations(teamImportDto)
                .forEach(v -> sb.append(INCORRECT_TEAM_MESSAGE).append(SEPARATOR));

      } else {

        Team team = this.mapper.map(teamImportDto, Team.class);

        Picture picture = this.pictureRepository
                .findByUrl(teamImportDto.getPictureImportDto().getUrl()).orElse(null);

        team.setPicture(picture);

        if (!this.validatorUtil.isValid(team) || picture == null) {
          this.validatorUtil.violations(team)
                  .forEach(v -> sb.append(INCORRECT_TEAM_MESSAGE).append(SEPARATOR));

          continue;
        }

        this.teamRepository.saveAndFlush(team);

        sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
                team.getClass().getSimpleName(),
                team.getName()))
                .append(SEPARATOR);
      }
    }

    return sb.toString().trim();
  }

  @Override
  public boolean areImported() {
    return this.teamRepository.count() > 0;
  }

  @Override
  public String readTeamsXmlFile() throws IOException {

    return this.fileUtil.readFile(TEAMS_FILE_INPUT_PATH);
  }
}