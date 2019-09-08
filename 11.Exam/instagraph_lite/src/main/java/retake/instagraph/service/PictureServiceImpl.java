package retake.instagraph.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retake.instagraph.domain.dtos.json.PictureImportDto;
import retake.instagraph.domain.entities.Picture;
import retake.instagraph.repository.PictureRepository;
import retake.instagraph.util.FileUtil;
import retake.instagraph.util.ValidationUtil;

import java.io.IOException;
import java.util.List;

import static retake.instagraph.common.Constants.*;


@Service
public class PictureServiceImpl implements PictureService {

  private final ValidationUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final Gson gson;
  private PictureRepository pictureRepository;

  @Autowired
  public PictureServiceImpl(PictureRepository pictureRepository,
                            ValidationUtil validatorUtil, FileUtil fileUtil,
                            ModelMapper mapper, Gson gson) {
    this.pictureRepository = pictureRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public Boolean picturesAreImported() {
    return this.pictureRepository.count() > 0;
  }

  @Override
  public String readPicturesFromJsonFile() throws IOException {
    return this.fileUtil.readFile(PATH_TO_FILES + "files/pictures.json");
  }

  @Override
  public String importPictures(String pictures) {

    StringBuilder sb = new StringBuilder();

    PictureImportDto[] pictureImportDtos = this.gson.fromJson(pictures, PictureImportDto[].class);

    for (PictureImportDto pictureImportDto : pictureImportDtos) {

      Picture picture = this.mapper.map(pictureImportDto, Picture.class);

      if (!this.validatorUtil.isValid(picture)) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      this.pictureRepository.saveAndFlush(picture);

      sb.append(String.format(SUCCESSFUL_PICTURE_IMPORT_MESSAGE,
              picture.getPath(),
              picture.getSize()))
              .append(SEPARATOR);
    }

    return sb.toString().trim();
  }

  @Override
  public String exportPictures() {
    StringBuilder sb = new StringBuilder();

    List<Picture> pictures = this.pictureRepository.findAllBySizeAfterOrderBySizeAsc(TEST_SIZE);

    for (Picture picture : pictures) {

      String[] pictureTokens = picture.getPath().split("/");

      String postName = pictureTokens[pictureTokens.length - 1];

      sb.append(String.format("%s - %.2f", postName, picture.getSize())).append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}
