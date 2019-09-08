package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.xml.PictureImportDto;
import softuni.exam.domain.dtos.xml.PictureImportRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParserUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static softuni.exam.common.Constants.*;

@Service
public class PictureServiceImpl implements PictureService {

  private final PictureRepository pictureRepository;
  private final ValidatorUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final XmlParserUtil xmlParserUtil;

  @Autowired
  public PictureServiceImpl(PictureRepository pictureRepository, ValidatorUtil validatorUtil,
                            FileUtil fileUtil, ModelMapper mapper, XmlParserUtil xmlParserUtil) {
    this.pictureRepository = pictureRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.xmlParserUtil = xmlParserUtil;
  }

  @Override
  public String importPictures() throws IOException, JAXBException {

    StringBuilder sb = new StringBuilder();

    PictureImportRootDto pictureImportRootDtos =
            this.xmlParserUtil.parseXml(PictureImportRootDto.class, PICTURES_FILE_INPUT_PATH);

    for (PictureImportDto importDto : pictureImportRootDtos.getImportDtos()) {

      Picture picture = this.mapper.map(importDto, Picture.class);


      if (!this.validatorUtil.isValid(picture)) {
        this.validatorUtil.violations(picture)
                .forEach(v -> sb.append(INCORRECT_PICTURE_MESSAGE).append(SEPARATOR));
        continue;
      }

      this.pictureRepository.saveAndFlush(picture);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              picture.getClass().getSimpleName(),
              picture.getUrl()))
              .append(SEPARATOR);

//      if (!this.validatorUtil.isValid(importDto)) {
//        this.validatorUtil.violations(importDto)
//                .forEach(v -> sb.append(INCORRECT_PICTURE_MESSAGE).append(SEPARATOR));
//
//      } else {
//
//        Picture picture = this.mapper.map(importDto, Picture.class);
//
//        if (!this.validatorUtil.isValid(picture)) {
//          this.validatorUtil.violations(picture)
//                  .forEach(v -> sb.append(INCORRECT_PICTURE_MESSAGE).append(SEPARATOR));
//          continue;
//        }
//
//        this.pictureRepository.saveAndFlush(picture);
//
//        sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
//                picture.getClass().getSimpleName(),
//                picture.getUrl()))
//                .append(SEPARATOR);
//      }
    }

    return sb.toString().trim();
  }

  @Override
  public boolean areImported() {
    return this.pictureRepository.count() > 0;
  }

  @Override
  public String readPicturesXmlFile() throws IOException {

    return this.fileUtil.readFile(PICTURES_FILE_INPUT_PATH);
  }
}