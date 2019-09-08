package lab.demoPartTwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utilities.Constants.GET_GAMES_PLAYED_BY_USER;

public class Engine implements Runnable {

  private Connection connection;

  public Engine(Connection connection) {
    this.connection = connection;
  }

  public void run() {
    try {
      System.out.println(this.getGamesPlayedByUser());
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }

  private String getGamesPlayedByUser() throws SQLException, IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    PreparedStatement statement =
            this.connection.prepareStatement(GET_GAMES_PLAYED_BY_USER);
    String username = reader.readLine();
    statement.setString(1, username);

    ResultSet rs = statement.executeQuery();

    if (!rs.next()) {
      sb.append("No such user exists");
    } else {
      sb.append(String.format("User: %s%n", username));
      sb.append(String.format("%s has played %s games",
              rs.getString("full_name"),
              rs.getString("count")));
    }

    return sb.toString().trim();
  }

}
