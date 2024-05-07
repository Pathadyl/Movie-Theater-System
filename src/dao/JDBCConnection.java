package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final String url = "jdbc:mysql://localhost:3306/movie_theater_project";
    private static final String user = "root";
    private static final String password = "Dylanp240502@";

    public static Connection getJDBCConnection(){
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
