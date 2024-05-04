package dao;

import model.Movie;
import model.Theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TheaterDao {
    public ArrayList<Theater> getALlTheater(){                   // Get/Show all theaters
        Connection connection =JDBCConnection.getJDBCConnection();
        ArrayList<Theater> theaters = new ArrayList<>();
        String sql = "SELECT * FROM theater";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Theater theater = new Theater();
                theater.setName(rs.getString("name"));
                theater.setLocation(rs.getString("location"));

                theaters.add(theater);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return theaters;
    }

    public void addTheater(String name, String location){
        Connection connection =JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO theater(name, location) VALUES(?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, location);
            int rs = preparedStatement.executeUpdate();
            if(rs > 0){
                System.out.println("Add theater success!");
            }
            else {
                System.out.println("Nothing was inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTheaterByID(int id){
        Connection connection =JDBCConnection.getJDBCConnection();
        String sql = "DELETE FROM theater WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editTheaterByID(int id, String name, String location){
        Connection connection =JDBCConnection.getJDBCConnection();
        String sql = "UPDATE theater SET name = ?, location = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, location);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
