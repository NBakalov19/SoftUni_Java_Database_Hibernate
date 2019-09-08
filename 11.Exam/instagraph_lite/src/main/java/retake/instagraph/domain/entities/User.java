package retake.instagraph.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Column(unique = true)
  @NotNull
  private String username;

  @Column
  @NotNull
  private String password;

  @ManyToOne(targetEntity = Picture.class)
  @JoinColumn(name = "profile_picture_id", referencedColumnName = "id")
  @NotNull
  private Picture profilePicture;

  @OneToMany(targetEntity = Post.class, mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Post> posts;

  public User() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Picture getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(Picture profilePicture) {
    this.profilePicture = profilePicture;
  }

  public Set<Post> getPosts() {
    return posts;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
  }
}
