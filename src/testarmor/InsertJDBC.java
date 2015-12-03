package testarmor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertJDBC {

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "Chicony13";

    public static void main(String[] argv) {

        try {

            insertRecordIntoDbHumanTable();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    private static void insertRecordIntoDbHumanTable() throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;

        int age = 24;
        String f_name = "Anton Test3";
        String l_name = "Semenov";

        String insertTableSQL = "BEGIN INSERT INTO human_table (name) " +
                                "VALUES (human_typ(" + age + ",'" +
                                                    f_name + "','" +
                                                    l_name + "'));" +
                                "END;";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // execute insert SQL statement
            statement.executeUpdate(insertTableSQL);

            System.out.println(insertTableSQL);
            System.out.println("Ваши данные успешно добавлены в таблицу human_table");

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

            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

}