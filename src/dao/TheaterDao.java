package dao;

import model.Role;
import model.Theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheaterDao {
    public List<Theater> getAllTheaters() {
        List<Theater> theaters = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM theater";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while(rs.next()) {
                String name = rs.getString("name");
                String location = rs.getString("location");
                boolean visibility = rs.getBoolean("visibility");

                Theater theater = new Theater(name, location, visibility);

                theaters.add(theater);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theaters;
    }

    public List<Theater> searchTheaterByName(String n, Role role) {
        List<Theater> theaters = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM theater" +
                " WHERE LOWER(name) LIKE LOWER(?)";
        if(role == Role.CUSTOMER)
            sql += " AND visibility = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + n + "%");
            if(role == Role.CUSTOMER)
                preparedStatement.setBoolean(2, true);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name");
                String location = rs.getString("location");
                boolean visibility = rs.getBoolean("visibility");

                Theater theater = new Theater(name, location, visibility);
                theaters.add(theater);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theaters;
    }

    public void addTheater(String name, String location, boolean visibility){
        Connection connection =JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO theater(name, location, visibility) VALUES(?, ?, ?)";
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
