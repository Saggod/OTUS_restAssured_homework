package dbConnecttions;

import java.sql.*;

public class DbDeleteQuery extends DbConnections {

    public void deletePetTestDbQuery(int i) {
        String deletePetQuery = "DELETE FROM pet WHERE pet_id = " + i;
        {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                System.out.println("Успешное подключение к базе данных!");

                ResultSet resultSet = statement.executeQuery(deletePetQuery);

                while (resultSet.next()) {
                    System.out.println("Удален Pet с ID = " + i);

                    resultSet.close();
                    statement.close();
                    connection.close();
                    System.out.println("Соединение закрыто.");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteOrderTest(int i) {
        String deletePetQuery = "DELETE FROM pet WHERE order_id = " + i;
        {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                System.out.println("Успешное подключение к базе данных!");

                ResultSet resultSet = statement.executeQuery(deletePetQuery);

                while (resultSet.next()) {
                    System.out.println("Удален Order с ID = " + i);

                    resultSet.close();
                    statement.close();
                    connection.close();
                    System.out.println("Соединение закрыто.");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
