package softuni.exam.common;

import java.math.BigDecimal;

public final class Constants {

  public static final String PICTURES_FILE_INPUT_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\files\\xml\\pictures.xml";

  public static final String TEAMS_FILE_INPUT_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\files\\xml\\teams.xml";

  public static final String PLAYERS_FILE_INPUT_PATH = System.getProperty("user.dir")
          + "\\src\\main\\resources\\files\\json\\players.json";

  public static final String SEPARATOR = System.lineSeparator();

  public final static String INCORRECT_TEAM_MESSAGE = "Invalid team";

  public final static String INCORRECT_PICTURE_MESSAGE = "Invalid picture";

  public final static String INCORRECT_PLAYER_MESSAGE = "Invalid player";

  public final static String SUCCESSFUL_IMPORT_MESSAGE = "Successfully imported %s – %s";

  public static final String TEAM_NAME = "North Hub";

  public static final BigDecimal SALARY = BigDecimal.valueOf(100000L);

  public Constants() {
  }

}
