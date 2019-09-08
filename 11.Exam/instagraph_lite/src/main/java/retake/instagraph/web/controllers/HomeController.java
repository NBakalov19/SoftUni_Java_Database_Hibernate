package retake.instagraph.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import retake.instagraph.service.PictureService;
import retake.instagraph.service.PostService;
import retake.instagraph.service.UserService;

@Controller
public class HomeController extends BaseController {

  private final PictureService pictureService;
  private final UserService userService;
  private final PostService postService;

  @Autowired
  public HomeController(PictureService pictureService, UserService userService, PostService postService) {

    this.pictureService = pictureService;
    this.userService = userService;
    this.postService = postService;
  }

  @GetMapping("/")
  public ModelAndView index() {
    boolean areImported = this.pictureService.picturesAreImported() && this.userService.usersAreImported() && this.postService.postsAreImported();

    return super.view("index", "areImported", areImported);
  }
}
