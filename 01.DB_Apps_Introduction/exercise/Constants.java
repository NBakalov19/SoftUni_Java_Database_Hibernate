package utilities;

public final class Constants {
  public static final String USERNAME = "root";
  public static final String PASSWORD = "1234";
  public static final String SOFTUNI_DATABASE_CONNECTION = "jdbc:mysql://localhost:3306/soft_uni";
  public static final String DIABLO_DATABASE_CONNECTION = "jdbc:mysql://localhost:3306/diablo";
  public static final String MINIONS_DATABASE_CONNECTION = "jdbc:mysql://localhost:3306/minions_db";

  public static final String GET_EMPLOYEE_BY_SALARY_QUERY =
          "SELECT first_name, last_name FROM employees WHERE salary > ?";

  public static final String GET_GAMES_PLAYED_BY_USER = "SELECT\n"
          + "CONCAT(u.first_name,' ',u.last_name) AS `full_name`,\n"
          + "COUNT(us.id) AS `count`\n"
          + "FROM users AS u\n"
          + "JOIN users_games AS us ON u.id = us.user_id\n"
          + "WHERE u.user_name = ?;";

  public static final String GET_VILLAINS_NAME_QUERY =
          "SELECT\n"
                  + "v.name,\n"
                  + "COUNT(mv.minion_id) AS `count`\n"
                  + "FROM villains AS v\n"
                  + "JOIN minions_villains mv on v.id = mv.villain_id\n"
                  + "GROUP BY mv.villain_id\n"
                  + "HAVING count > 15\n"
                  + "ORDER BY count DESC;";

  public static final String GET_VILLAIN_ID_BY_NAME = "SELECT id FROM villains WHERE name = ?";
  public static final String GET_VILLAIN_NAME_BY_ID = "SELECT name FROM villains WHERE id = ?;";
  public static final String GET_VILLAIN_NAME_BY_NAME = "SELECT name FROM villains WHERE name = ?";
  public static final String GET_MINIONS_BY_VILLAIN_ID =
          "SELECT m.name,m.age\n"
                  + "FROM minions AS m\n"
                  + "JOIN minions_villains mv on m.id = mv.minion_id\n"
                  + "JOIN villains v on mv.villain_id = v.id\n"
                  + "WHERE v.id = ?;\n";
  public static final String GET_TOWN_BY_NAME = "SELECT name FROM towns WHERE name = ?";
  public static final String GET_TOWN_ID_BY_NAME = "SELECT id FROM towns WHERE name = ?";
  public static final String GET_MINION_ID_BY_NAME = "SELECT id FROM minions WHERE name = ?";
  public static final String GET_MINION_NAME_AND_AGE = "SELECT name,age FROM minions WHERE id = ?";
  public static final String GET_MINIONS_COUNT_OF_VILLAIN =
          "SELECT COUNT(minion_id) AS count FROM minions_villains "
                  + "WHERE villain_id = ? GROUP BY villain_id;";
  public static final String GET_COUNTS_OF_TOWNS_BY_COUNTRY =
          "SELECT COUNT(name) AS count FROM towns WHERE country = ?;";
  public static final String GET_TOWNS_IN_COUNTRY = "SELECT name FROM towns WHERE country = ?;";
  public static final String GET_ALL_MINIONS_NAMES = "SELECT name FROM minions;";
  public static final String GET_ALL_MINIONS_NAME_AND_AGE = "SELECT name, age FROM minions";

  public static final String ADD_VILLAIN =
          "INSERT INTO villains(name,evilness_factor) VALUES (?,'evil');";
  public static final String ADD_TOWN = "INSERT INTO towns(name,country) VALUES (?,'Somewhere')";
  public static final String ADD_MINION = "INSERT INTO minions(name,age,town_id) VALUES (?, ?, ?)";
  public static final String ADD_MINION_TO_VIlLAIN = "INSERT INTO minions_villains VALUES (?,?);";

  public static final String HAVE_COUNTRY = "SELECT country FROM towns WHERE country = ?;";

  public static final String UPDATE_TOWNS =
          "UPDATE towns SET name = UPPER(name) WHERE country = ?;";
  public static final String UPDATE_MINIONS_AGE_AND_NAME =
          "UPDATE minions SET age = age + 1, name = LOWER(name) WHERE id = ?;";

  public static final String INCREASE_AGE_PROCEDURE = "CALL usp_get_older(?);";

  public static final String RELEASE_MINIONS = "DELETE FROM minions_villains WHERE villain_id = ?;";
  public static final String DELETE_VILLAIN = "DELETE FROM villains WHERE id = ?;";
}
