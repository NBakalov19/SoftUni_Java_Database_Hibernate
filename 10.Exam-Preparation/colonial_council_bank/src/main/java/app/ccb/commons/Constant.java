package app.ccb.commons;

public final class Constant {

  public final static String SEPARATOR = System.lineSeparator();

  public static final String BRANCHES_JSON_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\files\\json\\branches.json";

  public static final String CLIENTS_JSON_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\files\\json\\clients.json";

  public static final String EMPLOYEES_JSON_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\files\\json\\employees.json";

  public static final String BANK_ACCOUNTS_XML_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\files\\xml\\bank-accounts.xml";

  public static final String CARDS_XML_FILE_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\files\\xml\\cards.xml";

  public final static String INCORRECT_DATA_MESSAGE = "Error: Incorrect Data!";

  public final static String SUCCESSFUL_IMPORT_MESSAGE = "Successfully imported %s – %s.";

}
