package dao;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static java.sql.Connection getJDBCConnection(){
        final String url = "jdbc:mysql://localhost:3306/movie_theater_project";
        final String user = "root";
        final String password = "Dylanp240502@";

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
