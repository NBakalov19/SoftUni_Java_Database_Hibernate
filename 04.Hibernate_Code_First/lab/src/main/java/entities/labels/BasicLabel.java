package entities.labels;

import entities.shampoos.BasicShampoo;
import interfaces.Label;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel implements Label {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  private String title;

  private String subtitle;

  @OneToOne(mappedBy = "label", targetEntity = BasicShampoo.class, cascade = CascadeType.ALL)
  private BasicShampoo basicShampoo;

  public BasicLabel() {
  }

  public BasicLabel(String title, String subtitle) {
    this.setTitle(title);
    this.setSubtitle(subtitle);
  }

  @Override
  public long getId() {
    return this.id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String getSubtitle() {
    return this.subtitle;
  }

  @Override
  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public BasicShampoo getBasicShampoo() {
    return basicShampoo;
  }

  public void setBasicShampoo(BasicShampoo basicShampoo) {
    this.basicShampoo = basicShampoo;
  }
}
