package retake.instagraph.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retake.instagraph.domain.dtos.json.UserImportDto;
import retake.instagraph.domain.entities.Picture;
import retake.instagraph.domain.entities.Post;
import retake.instagraph.domain.entities.User;
import retake.instagraph.repository.PictureRepository;
import retake.instagraph.repository.UserRepository;
import retake.instagraph.util.FileUtil;
import retake.instagraph.util.ValidationUtil;

import java.io.IOException;
import java.util.Set;

import static retake.instagraph.common.Constants.*;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PictureRepository pictureRepository;
  private final ValidationUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final Gson gson;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
                         PictureRepository pictureRepository,
                         ValidationUtil validatorUtil, FileUtil fileUtil,
                         ModelMapper mapper, Gson gson) {
    this.userRepository = userRepository;
    this.pictureRepository = pictureRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.gson = gson;
  }

  @Override
  public String readUsersFromJson() throws IOException {
    return this.fileUtil.readFile(PATH_TO_FILES + "files/users.json");
  }

  @Override
  public Boolean usersAreImported() {

    return this.userRepository.count() > 0;
  }

  @Override
  public String importUsers(String users) {
    StringBuilder sb = new StringBuilder();

    UserImportDto[] userImportDtos = this.gson.fromJson(users, UserImportDto[].class);

    for (UserImportDto userImportDto : userImportDtos) {

      User user = this.mapper.map(userImportDto, User.class);

      Picture picture = this.pictureRepository
              .findByPath(userImportDto.getProfilePicturePath());

      User isAlreadySaveUser = this.userRepository.findByUsername(userImportDto.getUsername());

      user.setProfilePicture(picture);

      if (!this.validatorUtil.isValid(user) || isAlreadySaveUser != null) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      this.userRepository.saveAndFlush(user);

      sb.append(String.format(SUCCESSFUL_USER_IMPORT_MESSAGE,
              user.getUsername()))
              .append(SEPARATOR);
    }


    return sb.toString().trim();
  }

  @Override
  public String exportUsersWithTheirPosts() {
    StringBuilder sb = new StringBuilder();

    Set<User> getUsersWithPosts = this.userRepository.findAllByPosts();

    for (User user : getUsersWithPosts) {

      sb.append(String.format("%d. %s - %d posts",
              COUNTER++,
              user.getUsername(),
              user.getPosts().size()))
              .append(SEPARATOR);

      sb.append("Post Details:").append(SEPARATOR);

      for (Post post : user.getPosts()) {


        String[] postPath = post.getPicture().getPath().split("/");

        String postName = postPath[postPath.length - 1];

        sb.append(String.format("-Caption: %s", post.getCaption())).append(SEPARATOR);
        sb.append(String.format("-Picture: %s", postName)).append(SEPARATOR);
      }
    }

    return sb.toString().trim();

  }
}
