package app.ccb.domain.dtos.xml.cardsXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardImportRootDto implements Serializable {

  @XmlElement(name = "card")
  private List<CardImportDto> cardImportDtos;

  public CardImportRootDto() {
    this.cardImportDtos = new ArrayList<>();
  }

  public List<CardImportDto> getCardImportDtos() {
    return cardImportDtos;
  }

  public void setCardImportDtos(List<CardImportDto> cardImportDtos) {
    this.cardImportDtos = cardImportDtos;
  }
}

