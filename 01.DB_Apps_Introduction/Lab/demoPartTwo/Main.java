package lab.demoPartTwo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static utilities.Constants.*;

public class Main {
  public static void main(String[] args) throws SQLException {

    Properties properties = new Properties();
    properties.setProperty("user", USERNAME);
    properties.setProperty("password", PASSWORD);

    Connection connection = DriverManager.getConnection(DIABLO_DATABASE_CONNECTION, properties);

    Engine engine = new Engine(connection);
    engine.run();
  }
}