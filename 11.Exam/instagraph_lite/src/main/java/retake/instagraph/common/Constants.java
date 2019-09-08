package retake.instagraph.common;

public final class Constants {

  public final static String PATH_TO_FILES = System.getProperty("user.dir")
          + "/src/main/resources/";

  public final static String SUCCESSFUL_PICTURE_IMPORT_MESSAGE =
          "Picture with path: %s and size: %s successfully imported.";

  public final static String SUCCESSFUL_USER_IMPORT_MESSAGE = "User %s successfully imported.";

  public final static String SUCCESSFUL_POST_IMPORT_MESSAGE = "Post %s successfully imported.";

  public static final String SEPARATOR = System.lineSeparator();

  public final static String INCORRECT_DATA_MESSAGE = "Error: Incorrect data.";
  public static final Double TEST_SIZE = 30000d;
  public static Integer COUNTER = 1;

  public Constants() {
  }
}
