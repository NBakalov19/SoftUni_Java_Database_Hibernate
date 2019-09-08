package retake.instagraph.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import retake.instagraph.service.PictureService;
import retake.instagraph.service.PostService;
import retake.instagraph.service.UserService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {
  private final PictureService pictureService;
  private final UserService userService;
  private final PostService postService;


  @Autowired
  public ImportController(PictureService pictureService, UserService userService, PostService postService) {
    this.pictureService = pictureService;
    this.userService = userService;
    this.postService = postService;
  }

  @GetMapping("/json")
  public ModelAndView importJson() {
    boolean[] areImported = new boolean[]{
            this.pictureService.picturesAreImported(),
            this.userService.usersAreImported()
    };

    return super.view("json/import-json", "areImported", areImported);
  }

  @GetMapping("/pictures")
  public ModelAndView importPicture() throws IOException {
    String picturesFromJson = this.pictureService.readPicturesFromJsonFile();
    return super.view("json/import-pictures", "pictures", picturesFromJson);
  }

  @PostMapping("/pictures")
  public ModelAndView importPicturesConfirm(@RequestParam("pictures") String pictures) {
    System.out.println(this.pictureService.importPictures(pictures));

    return super.redirect("/import/json");
  }

  @GetMapping("/users")
  public ModelAndView importUsers() throws IOException {
    String usersFromJson = this.userService.readUsersFromJson();
    return super.view("json/import-users", "users", usersFromJson);
  }

  @PostMapping("/users")
  public ModelAndView importUsersConfirm(@RequestParam("users") String users) {
    System.out.println(this.userService.importUsers(users));

    return super.redirect("/import/json");
  }

  @GetMapping("/xml")
  public ModelAndView importXml() {
    boolean[] areImported = new boolean[]{
            this.postService.postsAreImported()
    };

    return super.view("xml/import-xml", "areImported", areImported);
  }

  @GetMapping("/posts")
  public ModelAndView importPost() throws IOException {
    String postsFromXml = this.postService.readPostFromXmlFile();
    return super.view("xml/import-posts", "posts", postsFromXml);
  }

  @PostMapping("/posts")
  public ModelAndView importPostsConfirm(@RequestParam("posts") String posts) throws JAXBException, FileNotFoundException {
    System.out.println(this.postService.importPosts(posts));

    return super.redirect("/import/xml");
  }
}
