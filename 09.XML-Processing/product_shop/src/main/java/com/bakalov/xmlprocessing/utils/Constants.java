package com.bakalov.xmlprocessing.utils;

import java.math.BigDecimal;

public final class Constants {
  public static final String USER_INPUT_XML_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\productShopXmls\\input\\users.xml";

  public static final String CATEGORY_INPUT_XML_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\productShopXmls\\input\\categories.xml";

  public static final String PRODUCT_INPUT_XML_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\productShopXmls\\input\\products.xml";

  public static final String PRODUCT_IN_RANGE_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\productShopXmls\\output\\products-in-range.xml";

  public static final String SUCCESSFULLY_SELLERS_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\productShopXmls\\output\\users-sold-products.xml";

  public static final String CATEGORIES_BY_PRODUCTS = System.getProperty("user.dir")
          + "\\src\\main\\resources\\productShopXmls\\output\\categories-by-products.xml";

  public static final String USERS_AND_PRODUCTS_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\productShopXmls\\output\\users-and-products.xml";

  public static final BigDecimal MORE = BigDecimal.valueOf(500);
  public static final BigDecimal LESS = BigDecimal.valueOf(1000);

  public Constants() {
  }
}
