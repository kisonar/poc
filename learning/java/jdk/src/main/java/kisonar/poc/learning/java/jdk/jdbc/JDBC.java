package kisonar.poc.learning.java.jdk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.157.50.116:1521:xe", "System", "system")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
