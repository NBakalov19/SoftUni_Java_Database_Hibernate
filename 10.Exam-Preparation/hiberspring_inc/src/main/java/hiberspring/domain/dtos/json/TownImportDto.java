package hiberspring.domain.dtos.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;


public class TownImportDto implements Serializable {

  @Expose
  private String name;

  @Expose
  private Integer population;

  public TownImportDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPopulation() {
    return population;
  }

  public void setPopulation(Integer population) {
    this.population = population;
  }
}
