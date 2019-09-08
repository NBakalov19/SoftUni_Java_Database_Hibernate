package lab.demoPartOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utilities.Constants.GET_EMPLOYEE_BY_SALARY_QUERY;

public class Engine implements Runnable {

  private Connection connection;

  public Engine(Connection connection) {
    this.connection = connection;
  }

  public void run() {
    try {
      System.out.println(this.getEmployeeBySalary());
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }

  private String getEmployeeBySalary() throws SQLException, IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    PreparedStatement statement =
            this.connection.prepareStatement(GET_EMPLOYEE_BY_SALARY_QUERY);
    double salary = Double.parseDouble(reader.readLine());

    statement.setDouble(1, salary);

    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {

      sb.append(String.format("%s %s%n",
              resultSet.getString("first_name"),
              resultSet.getString("last_name")));
    }

    return sb.toString().trim();
  }

}
