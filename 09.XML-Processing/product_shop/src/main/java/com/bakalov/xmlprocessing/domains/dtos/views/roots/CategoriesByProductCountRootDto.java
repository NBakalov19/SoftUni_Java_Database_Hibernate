package com.bakalov.xmlprocessing.domains.dtos.views.roots;

import com.bakalov.xmlprocessing.domains.dtos.views.CategoriesByProductCountDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductCountRootDto implements Serializable {

  @XmlElement(name = "category")
  private List<CategoriesByProductCountDto> categories;

  public CategoriesByProductCountRootDto() {
  }

  public List<CategoriesByProductCountDto> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoriesByProductCountDto> categories) {
    this.categories = categories;
  }
}
