package com.bakalov.gamestoredatabase.domains.dtos;

import java.math.BigDecimal;

public class GameTitleAndPriceDto {

  private String title;
  private BigDecimal price;

  public GameTitleAndPriceDto() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
