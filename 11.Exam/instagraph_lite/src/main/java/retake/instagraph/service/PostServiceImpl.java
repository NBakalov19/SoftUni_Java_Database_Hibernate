package retake.instagraph.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retake.instagraph.domain.dtos.xml.PostImportDto;
import retake.instagraph.domain.dtos.xml.PostImportRootDto;
import retake.instagraph.domain.entities.Picture;
import retake.instagraph.domain.entities.Post;
import retake.instagraph.domain.entities.User;
import retake.instagraph.repository.PictureRepository;
import retake.instagraph.repository.PostRepository;
import retake.instagraph.repository.UserRepository;
import retake.instagraph.util.FileUtil;
import retake.instagraph.util.ValidationUtil;
import retake.instagraph.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static retake.instagraph.common.Constants.*;


@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final PictureRepository pictureRepository;
  private final ValidationUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final XmlParser xmlParser;

  @Autowired
  public PostServiceImpl(PostRepository postRepository,
                         UserRepository userRepository,
                         PictureRepository pictureRepository,
                         ValidationUtil validatorUtil, FileUtil fileUtil,
                         ModelMapper mapper, XmlParser xmlParser) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
    this.pictureRepository = pictureRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.xmlParser = xmlParser;
  }

  @Override
  public String readPostFromXmlFile() throws IOException {
    return this.fileUtil.readFile(PATH_TO_FILES + "files/posts.xml");
  }

  @Override
  public String importPosts(String posts) throws JAXBException, FileNotFoundException {
    StringBuilder sb = new StringBuilder();

    PostImportRootDto postImportRootDto =
            this.xmlParser.parseXml(PostImportRootDto.class, PATH_TO_FILES + "files/posts.xml");

    for (PostImportDto postImportDto : postImportRootDto.getPostImportDtos()) {

      Post post = this.mapper.map(postImportDto, Post.class);

      User user = this.userRepository.findByUsername(postImportDto.getUsername());

      Picture picture = this.pictureRepository.findByPath(postImportDto.getPicture());

      post.setUser(user);
      post.setPicture(picture);

      if (!this.validatorUtil.isValid(post)) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      this.postRepository.saveAndFlush(post);

      sb.append(String.format(SUCCESSFUL_POST_IMPORT_MESSAGE,
              post.getCaption()))
              .append(SEPARATOR);
    }
    return sb.toString().trim();
  }

  @Override
  public Boolean postsAreImported() {
    return this.postRepository.count() > 0;
  }
}
