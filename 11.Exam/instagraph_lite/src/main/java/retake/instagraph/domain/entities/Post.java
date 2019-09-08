package retake.instagraph.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

  @Column
  @NotNull
  private String caption;

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @NotNull
  private User user;

  @ManyToOne(targetEntity = Picture.class)
  @JoinColumn(name = "picture_id", referencedColumnName = "id")
  @NotNull
  private Picture picture;

  public Post() {
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Picture getPicture() {
    return picture;
  }

  public void setPicture(Picture picture) {
    this.picture = picture;
  }
}
