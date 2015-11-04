package sample.java.jdbc;

import java.sql.*;
import java.util.Random;
import java.util.UUID;

/**
 * Created by kopelevi on 03/11/2015.
 */
public class SimpleConnection {

    public static void main(String[] args) {
        String url = "jdbc:h2:~/test";   //database specific url.
        String user = "sa";
        String password = "";

        String createTablePeople = "CREATE TABLE PEOPLE (ID INT, NAME VARCHAR(50))";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatementCreate = connection.prepareStatement(createTablePeople,
                     ResultSet.TYPE_FORWARD_ONLY,
                     ResultSet.CONCUR_READ_ONLY,
                     ResultSet.CLOSE_CURSORS_AT_COMMIT);
             Statement statement = connection.createStatement()) {

//            boolean result = preparedStatementCreate.execute(createTablePeople);

            String insertPeopleSql = "INSERT INTO PEOPLE VALUES (" + new Random().nextInt(1000) + ", '" + UUID.randomUUID() + "'), (" + new Random().nextInt(1000) + " , '" + UUID.randomUUID() + "')";
            boolean result = statement.execute(insertPeopleSql);

            String sql = "select * from PEOPLE";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                System.out.println("ID:" + id + ", NAME:" + name);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
