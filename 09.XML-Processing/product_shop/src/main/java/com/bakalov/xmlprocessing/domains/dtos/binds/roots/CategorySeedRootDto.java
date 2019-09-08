package com.bakalov.xmlprocessing.domains.dtos.binds.roots;

import com.bakalov.xmlprocessing.domains.dtos.binds.CategorySeedDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedRootDto implements Serializable {

  @XmlElement(name = "category")
  private List<CategorySeedDto> categorySeedDtos;


  public CategorySeedRootDto() {
    this.categorySeedDtos = new ArrayList<>();
  }

  public List<CategorySeedDto> getCategorySeedDtos() {
    return categorySeedDtos;
  }

  public void setCategorySeedDtos(List<CategorySeedDto> categorySeedDtos) {
    this.categorySeedDtos = categorySeedDtos;
  }
}
