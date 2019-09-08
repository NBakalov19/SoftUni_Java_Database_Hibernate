package com.bakalov.xmlprocessing.domains.dtos.views.roots;

import com.bakalov.xmlprocessing.domains.dtos.views.SuccessfullySellersDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuccessfullySellersRootDto implements Serializable {

  @XmlElement(name = "user")
  private List<SuccessfullySellersDto> sellersDtos;

  public SuccessfullySellersRootDto() {
    this.sellersDtos = new ArrayList<>();
  }

  public List<SuccessfullySellersDto> getSellersDtos() {
    return sellersDtos;
  }

  public void setSellersDtos(List<SuccessfullySellersDto> sellersDtos) {
    this.sellersDtos = sellersDtos;
  }
}
