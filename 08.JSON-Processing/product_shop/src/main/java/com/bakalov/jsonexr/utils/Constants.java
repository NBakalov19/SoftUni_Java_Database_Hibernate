package com.bakalov.jsonexr.utils;

import java.math.BigDecimal;

public final class Constants {
  public static final String USER_JSON_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\product_shop_json\\users.json";

  public static final String CATEGORY_JSON_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\product_shop_json\\categories.json";

  public static final String PRODUCT_JSON_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\product_shop_json\\products.json";

  public static final String PRODUCT_IN_RANGE_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\product_shop_json\\output\\product-in-range.json";

  public static final String SUCCESSFULLY_SELLERS_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\product_shop_json\\output\\users-sold-products.json";

  public static final String CATEGORIES_BY_PRODUCTS = System.getProperty("user.dir")
          + "\\src\\main\\resources\\product_shop_json\\output\\categories-by-products.json";

  public static final String USERS_AND_PRODUCTS_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\product_shop_json\\output\\users-and-products.json";

  public static final BigDecimal MORE = BigDecimal.valueOf(500);
  public static final BigDecimal LESS = BigDecimal.valueOf(1000);

  public Constants() {
  }
}
