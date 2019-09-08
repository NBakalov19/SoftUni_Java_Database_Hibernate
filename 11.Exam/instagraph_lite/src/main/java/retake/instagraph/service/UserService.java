package retake.instagraph.service;

import java.io.IOException;

public interface UserService {
  String readUsersFromJson() throws IOException;

  Boolean usersAreImported();

  String importUsers(String users);

  String exportUsersWithTheirPosts();
}
