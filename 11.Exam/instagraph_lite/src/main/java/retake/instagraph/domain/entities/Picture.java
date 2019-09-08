package retake.instagraph.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

  @Column
  @NotNull
  private String path;

  @Column
  @NotNull
  private Double size;

  @OneToMany(targetEntity = User.class, mappedBy = "profilePicture", cascade = CascadeType.ALL)
  private Set<User> user;

  @OneToMany(targetEntity = Post.class, mappedBy = "picture", cascade = CascadeType.ALL)
  private Set<Post> posts;

  public Picture() {
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }

  public Set<User> getUser() {
    return user;
  }

  public void setUser(Set<User> user) {
    this.user = user;
  }

  public Set<Post> getPosts() {
    return posts;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
  }
}
