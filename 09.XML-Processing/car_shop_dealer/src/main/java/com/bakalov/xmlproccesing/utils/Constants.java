package com.bakalov.xmlproccesing.utils;

public final class Constants {
  public static final String SUPPLIER_XML_INPUT_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\carDealerXmlData\\input\\suppliers.xml";

  public static final String PARTS_XML_INPUT_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\carDealerXmlData\\input\\parts.xml";

  public static final String CARS_XML_INPUT_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\carDealerXmlData\\input\\cars.xml";

  public static final String CUSTOMERS_XML_INPUT_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\carDealerXmlData\\input\\customers.xml";

  public static final String ORDERED_CUSTOMERS_OUTPUT_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\carDealerXmlData\\output\\ordered-customers.xml";

  public static final String TOYOTA_CARS_OUTPUT_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\carDealerXmlData\\output\\toyota-cars.xml";

  public static final String LOCAL_SUPPLIER_OUTPUT_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\carDealerXmlData\\output\\local-suppliers.xml";

  public static final String CAR_AND_PART_OUTPUT_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\carDealerXmlData\\output\\cars-and-parts.xml";

  public static final String CUSTOMERS_AND_PURCHASES_OUTPUT_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\carDealerXmlData\\output\\customers-total-sales.xml";

  public static final String SALES_DETAILS_OUTPUT_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\carDealerXmlData\\output\\sales-discounts.xml";

  public static final String TOYOTA_CAR = "Toyota";

  public static final Integer CAR_MIN_PARTS_BOUND = 10;
  public static final Integer CAR_MAX_PARTS_BOUND = 20;


  public Constants() {
  }
}
