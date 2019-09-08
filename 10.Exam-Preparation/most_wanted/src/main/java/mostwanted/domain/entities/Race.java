package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "races")
public class Race extends BaseEntity {

  @Column(nullable = false, columnDefinition = "integer default 0")
  private Integer laps;

  @ManyToOne(targetEntity = District.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false)
  private District district;

  @OneToMany(mappedBy = "race"/*,fetch = FetchType.EAGER*/)
  private List<RaceEntry> entries;

  public Race() {
    this.entries = new ArrayList<>();
  }

  public Integer getLaps() {
    return laps;
  }

  public void setLaps(Integer laps) {
    this.laps = laps;
  }

  public District getDistrict() {
    return district;
  }

  public void setDistrict(District district) {
    this.district = district;
  }

  public List<RaceEntry> getEntries() {
    return Collections.unmodifiableList(entries);
  }

  public void setEntries(List<RaceEntry> entries) {
    this.entries = entries;
  }
}
