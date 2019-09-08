package com.bakalov.gamestoredatabase.domains.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "games")
public class Game extends BaseEntity {
  private String title;
  private String trailer;
  private String imageThumbnail;
  private Double size;
  private BigDecimal price;
  private String description;
  private LocalDate releaseDate;
  private Set<User> users;

  public Game() {
  }

  @Column(name = "title", nullable = false, unique = true)
  @Pattern(
          regexp = "^(?=[A-Z]).*$",
          message = "title must start with uppercase letter and must be between 3 and 100 symbols")
  @Size(min = 3, max = 100)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  @Column(name = "trailer", unique = true)
  @Pattern(regexp = "^[a-zA-Z0-9]+$",
          message = "Invalid trailer ID!")
  @Size(min = 11, max = 11)
  public String getTrailer() {
    return trailer;
  }

  public void setTrailer(String trailer) {
    this.trailer = trailer;
  }

  @Column(name = "image_thumbnail", unique = true, length = 100)
  @Pattern(regexp = "(https)?(http)?://.+",
          message = "Invalid thumbnail URL!")
  public String getImageThumbnail() {
    return imageThumbnail;
  }

  public void setImageThumbnail(String imageThumbnail) {
    this.imageThumbnail = imageThumbnail;
  }


  @Column(name = "size", nullable = false, precision = 10, scale = 1)
  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }

  @Column(name = "price", nullable = false, precision = 19, scale = 2)
  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Column(name = "description", nullable = false)
  @Size(min = 20, max = 1000)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "release_date", nullable = false)
  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  @ManyToMany(targetEntity = User.class, mappedBy = "games")
  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }
}
