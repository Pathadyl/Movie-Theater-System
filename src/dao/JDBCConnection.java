package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class JDBCConnection {
    private static final String url = "jdbc:mysql://localhost:3306/movie_theater_project";
    private static final String user = "root";
    private static final String password = "Dylanp240502@";

    public static Connection getJDBCConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
