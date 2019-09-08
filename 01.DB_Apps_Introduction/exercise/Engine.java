package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;
import static utilities.Constants.*;

public class Engine implements Runnable {

  private Properties properties;
  private Connection connection;

  public Engine() throws SQLException {
    this.setProperties();
    this.connection = getConnection(MINIONS_DATABASE_CONNECTION, this.properties);
  }

  private void setProperties() {
    this.properties = new Properties();
    this.properties.setProperty("user", USERNAME);
    this.properties.setProperty("password", PASSWORD);
  }

  public void run() {

    //SELECT TASK TO DEBUG
    //THE CODE CAN BE REFACTORED MORE< BUt NO HAVE TIME :)
    try {
            /* 2.Get villains names
            System.out.println(this.getVillainsName());
            */

            /* 3.Get minions names
            System.out.println(this.getMinionsName());
            */

      /*4.Add Minion*/
      System.out.println(this.addMinionToVillain());

            /*5.Change town names casing
            System.out.println(this.changeTownNameCasing());
            */

            /*6.Remove villain
            System.out.println(this.removeVillain());
            */

            /*7.Print all minions
            System.out.println(this.printMinionsNames());
            */

            /*8.Increase minions age
            System.out.println(this.increaseMinionsAge());
            */

            /*9.Increase age with stored procedure
            System.out.println(this.increaseMinionAgeWithProcedure());
            */
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }

  //2.Get villains names
  private String getVillainsName() throws SQLException {
    StringBuilder output = new StringBuilder();

    PreparedStatement statement = this.connection.prepareStatement(GET_VILLAINS_NAME_QUERY);
    ResultSet resultSet = statement.executeQuery();

    while (resultSet.next()) {
      output.append(String.format("%s %s%n",
              resultSet.getString("name"),
              resultSet.getString("count")));
    }

    return output.toString().trim();
  }

  //3.Get minions names
  private String getMinionsName() throws IOException, SQLException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();
    int villainId = Integer.parseInt(reader.readLine());

    if (haveVillain(villainId)) {
      output.append(
              String.format("Villain: %s", getVillainNameByGivenId(villainId)))
              .append(System.lineSeparator());

      PreparedStatement minionsStatement = this.connection.prepareStatement(GET_MINIONS_BY_VILLAIN_ID);
      minionsStatement.setInt(1, villainId);
      ResultSet minionsResultSet = minionsStatement.executeQuery();

      int counter = 1;
      while (minionsResultSet.next()) {
        output.append(String.format("%d. %s %s%n",
                counter++,
                minionsResultSet.getString("name"),
                minionsResultSet.getString("age")));
      }
    } else {
      output.append(String.format("No villain with ID %s exists in the database.", villainId));
    }

    return output.toString().trim();
  }

  //4.Add minion
  private String addMinionToVillain() throws IOException, SQLException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();

    String[] minionData = reader.readLine().split("\\s+");
    String minionName = minionData[1];
    int minionAge = Integer.parseInt(minionData[2]);
    String minionTown = minionData[3];

    String villainName = reader.readLine().split("\\s+")[1];

    if (haveVillain(villainName) && haveTown(minionTown)) {
      try {
        this.connection.setAutoCommit(false);

        this.addMinionToDatabase(minionName, minionAge, this.getIdByGivenName(minionTown, GET_TOWN_ID_BY_NAME));
        this.addNewMinionToVillain(
                this.getIdByGivenName(villainName, GET_VILLAIN_ID_BY_NAME), this.getIdByGivenName(minionName, GET_MINION_ID_BY_NAME));

        this.connection.commit();
      } catch (SQLException e) {
        this.connection.rollback();
      }
      output.append(String.format("Successfully added %s to be minion of %s.", minionName, villainName));
    } else {
      try {
        this.connection.setAutoCommit(false);

        if (!haveTown(minionTown)) {
          this.addTown(minionTown);
          output.append(String.format("Town %s was added to the database.%n", minionTown));

        }

        if (!haveVillain(villainName)) {
          this.addVillainToDatabase(villainName);
          output.append(String.format("Villain %s was added to the database.%n", villainName));

        }

        this.addMinionToDatabase(minionName, minionAge, this.getIdByGivenName(minionTown, GET_TOWN_ID_BY_NAME));
        this.addNewMinionToVillain(
                this.getIdByGivenName(villainName, GET_VILLAIN_ID_BY_NAME),
                this.getIdByGivenName(minionName, GET_MINION_ID_BY_NAME));

        this.connection.commit();

        output.append(String.format("Successfully added %s to be minion of %s.", minionName, villainName));
      } catch (SQLException e) {
        this.connection.rollback();
      }
    }

    return output.toString().trim();
  }

  //5.Change town names casing
  private String changeTownNameCasing() throws IOException, SQLException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();

    String countryName = reader.readLine();

    if (haveCountry(countryName)) {
      int townsCount = this.getTownsCountByCountryName(countryName);
      output.append(String.format("%d town names were affected.%n", townsCount));

      updateTowns(countryName);

      List<String> towns = new ArrayList<>();
      getUpdatedTowns(countryName, towns);

      output.append(String.format("[%s]", String.join(", ", towns)));
    } else {
      output.append("No town names were affected");
    }
    return output.toString().trim();
  }

  //6.Remove villain
  private String removeVillain() throws IOException, SQLException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();

    int villainId = Integer.parseInt(reader.readLine());

    if (haveVillain(villainId)) {

      int minionsCount = getCountWithGivenId(villainId);
      String villainName = getVillainNameByGivenId(villainId);

      deleteVillain(villainId);

      output.append(String.format("%s was deleted", villainName))
              .append(System.lineSeparator())
              .append(String.format("%d minions released", minionsCount));


    } else {
      output.append("No such villain was found");
    }

    return output.toString();
  }

  private String getVillainNameByGivenId(int villainId) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
    statement.setInt(1, villainId);
    ResultSet resultSet = statement.executeQuery();
    String name = "";

    if (resultSet.next()) {
      name = resultSet.getString("name");
    }

    return name;
  }

  //7.Print minions Names
  private String printMinionsNames() throws SQLException {
    StringBuilder output = new StringBuilder();

    List<String> minions = new ArrayList<>();
    minions = getAllMinionsInSpecialOrder(minions);

    for (String minion : minions) {
      output.append(minion).append(System.lineSeparator());
    }


    return output.toString().trim();
  }

  //8.Increase minions age
  private String increaseMinionsAge() throws IOException, SQLException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();

    int[] minionsIds = Arrays.stream(reader.readLine().split("\\s+"))
            .mapToInt(Integer::parseInt)
            .toArray();

    for (int id : minionsIds) {
      updateMinionsAge(id);
    }

    PreparedStatement statement = this.connection.prepareStatement(GET_ALL_MINIONS_NAME_AND_AGE);
    ResultSet resultSet = statement.executeQuery();

    while (resultSet.next()) {
      output.append(String.format("%s %s%n",
              resultSet.getString("name"),
              resultSet.getString("age")));
    }

    return output.toString().trim();
  }

  //9.Increase age with stored procedure
  private String increaseMinionAgeWithProcedure() throws IOException, SQLException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();

    int minionId = Integer.parseInt(reader.readLine());
    increaseAgeProcedure(minionId);

    printMinionNameAndAge(output, minionId);

    return output.toString().trim();
  }

  private int getCountWithGivenId(int villainId) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(utilities.Constants.GET_MINIONS_COUNT_OF_VILLAIN);
    statement.setInt(1, villainId);
    ResultSet resultSet = statement.executeQuery();

    int minionsCount = 0;
    if (resultSet.next()) {
      minionsCount = resultSet.getInt("count");
    }
    return minionsCount;
  }

  private List<String> getAllMinionsInSpecialOrder(List<String> list) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(GET_ALL_MINIONS_NAMES);
    ResultSet resultSet = statement.executeQuery();

    while (resultSet.next()) {
      list.add(resultSet.getString("name"));
    }

    List<String> orderedList = new ArrayList<>();

    int decrement = 0;
    int increment = 0;
    for (int i = 0; i < list.size(); i++) {
      if (i % 2 == 0) {
        orderedList.add(list.get(i - decrement++));
      } else {
        orderedList.add(list.get(list.size() - 1 - increment++));
      }
    }

    return orderedList;
  }

  private void getUpdatedTowns(String countryName, List<String> list) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(GET_TOWNS_IN_COUNTRY);
    statement.setString(1, countryName);
    ResultSet resultSet = statement.executeQuery();

    while (resultSet.next()) {
      list.add(resultSet.getString("name"));
    }

  }

  private int getIdByGivenName(String name, String query) throws SQLException {
    PreparedStatement minionStatement = this.connection.prepareStatement(query);
    minionStatement.setString(1, name);
    ResultSet minionResult = minionStatement.executeQuery();

    int minionId = 0;
    if (minionResult.next()) {
      minionId = minionResult.getInt("id");
    }
    return minionId;
  }

  private int getTownsCountByCountryName(String countryName) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(GET_COUNTS_OF_TOWNS_BY_COUNTRY);
    statement.setString(1, countryName);
    ResultSet resultSet = statement.executeQuery();

    int count = 0;
    if (resultSet.next()) {
      count = resultSet.getInt("count");
    }

    return count;
  }

  private void increaseAgeProcedure(int minionId) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(INCREASE_AGE_PROCEDURE);
    statement.setInt(1, minionId);
    statement.executeQuery();
  }

  private boolean haveVillain(int id) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
    statement.setInt(1, id);
    ResultSet resultSet = statement.executeQuery();

    return resultSet.next();
  }

  private boolean haveVillain(String name) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(GET_VILLAIN_NAME_BY_NAME);
    statement.setString(1, name);
    ResultSet resultSet = statement.executeQuery();

    return resultSet.next();
  }

  private boolean haveTown(String town) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(GET_TOWN_BY_NAME);
    statement.setString(1, town);

    return statement.executeQuery().next();
  }

  private boolean haveCountry(String country) throws SQLException {
    PreparedStatement haveCountryStatement = this.connection.prepareStatement(HAVE_COUNTRY);
    haveCountryStatement.setString(1, country);

    return haveCountryStatement.executeQuery().next();
  }

  private void addVillainToDatabase(String name) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(ADD_VILLAIN);
    statement.setString(1, name);
    statement.executeUpdate();
  }

  private void addTown(String town) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(ADD_TOWN);
    statement.setString(1, town);
    statement.executeUpdate();
  }

  private void addNewMinionToVillain(int villainId, int minionId) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(ADD_MINION_TO_VIlLAIN);
    statement.setInt(1, minionId);
    statement.setInt(2, villainId);
    statement.executeUpdate();
  }

  private void addMinionToDatabase(String minionName, int minionAge, int town_id) throws SQLException {
    PreparedStatement minionStatement = this.connection.prepareStatement(ADD_MINION);
    minionStatement.setString(1, minionName);
    minionStatement.setInt(2, minionAge);
    minionStatement.setInt(3, town_id);
    minionStatement.executeUpdate();
  }

  private void updateTowns(String countryName) throws SQLException {
    PreparedStatement updateTownsStatement = this.connection.prepareStatement(UPDATE_TOWNS);
    updateTownsStatement.setString(1, countryName);
    updateTownsStatement.executeUpdate();
  }

  private void updateMinionsAge(int id) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(UPDATE_MINIONS_AGE_AND_NAME);
    statement.setInt(1, id);
    statement.executeUpdate();
  }

  private void deleteVillain(int villainId) throws SQLException {
    try {
      this.connection.setAutoCommit(false);

      PreparedStatement releaseMinionsStatement = this.connection.prepareStatement(RELEASE_MINIONS);
      releaseMinionsStatement.setInt(1, villainId);
      releaseMinionsStatement.executeUpdate();

      PreparedStatement statement = this.connection.prepareStatement(DELETE_VILLAIN);
      statement.setInt(1, villainId);
      statement.executeUpdate();

      this.connection.commit();
    } catch (SQLException e) {
      this.connection.rollback();
    }
  }

  private void printMinionNameAndAge(StringBuilder output, int minionId) throws SQLException {
    PreparedStatement statement = this.connection.prepareStatement(GET_MINION_NAME_AND_AGE);
    statement.setInt(1, minionId);

    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
      output.append(String.format("%s %s",
              resultSet.getString("name"),
              resultSet.getString("age")));
    }
  }
}