package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static java.sql.Connection getJDBCConnection(){
        final String url = "jdbc:mysql://localhost:3306/movie_theater_project";
        final String user = "root";
        final String password = "dunga3k46pbc2002";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                return connection;
            } else {
                System.out.println("Failed to establish connection to the database.");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
