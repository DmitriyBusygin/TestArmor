package testarmor;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectJDBC {

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "Chicony13";

    public static void main(String[] argv) {

        try {

            selectRecordsFromDbHumanTable();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    private static void selectRecordsFromDbHumanTable() throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;

        // Запрос к БД
        String selectTableSQL = "SELECT id, h.name.age, h.name.f_name, h.name.l_name from human_table h";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            System.out.println("Вывод значений колок в рамках тестового задания:");

            // execute select SQL statement
            ResultSet rs = statement.executeQuery(selectTableSQL);

            //Чтение и вывод строк
            while (rs.next()) {

                String id = rs.getString("id");
                int age = rs.getInt("name.age");
                String f_name = rs.getString("name.f_name");
                String l_name = rs.getString("name.l_name");

                System.out.println("ID : " + id +
                        ", age: " + age +
                        ", First name: " + f_name +
                        ", Last name: " + l_name);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }

    private static Connection getDBConnection() {

        Connection dbConnection = null;

        try {

            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

}