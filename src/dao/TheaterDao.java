package dao;

import model.Movie;
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
                theaters.add(mapResultSetToTheater(rs));
            }

            logForGetTheaterRequest(sql, theaters, "Get all theaters");

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + n + "%");
            if(role == Role.CUSTOMER)
                preparedStatement.setBoolean(2, true);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                theaters.add(mapResultSetToTheater(rs));
            }

            logForGetTheaterRequest(sql, theaters, "Get theaters by name");

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return theaters;
    }

    public void addTheaterToDB(String name, String location, boolean visibility){
        Connection connection =JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO theater(name, location, visibility) VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, location);
            int rs = preparedStatement.executeUpdate();

            logForModifyTheaterRequest(sql, "Add theater to DB", rs);

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void removeTheaterFromDB(int id){
        Connection connection =JDBCConnection.getJDBCConnection();
        String sql = "DELETE FROM theater WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rs = preparedStatement.executeUpdate();

            logForModifyTheaterRequest(sql, "Remove theater from DB", rs);

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void editTheaterFromDB(int id, String name, String location){
        Connection connection =JDBCConnection.getJDBCConnection();
        String sql = "UPDATE theater SET name = ?, location = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, location);
            preparedStatement.setInt(3, id);

            int rs = preparedStatement.executeUpdate();

            logForModifyTheaterRequest(sql, "Edit theater from DB", rs);

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Theater mapResultSetToTheater(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String location = rs.getString("location");
        boolean visibility = rs.getBoolean("visibility");

        return new Theater(name, location, visibility);
    }

    private void logForGetTheaterRequest(String sql, List<Theater> theaters, String typeRequest) {
        if(!theaters.isEmpty()) {
            System.out.println("\n(+)  " + typeRequest + " success!");
            System.out.println("(+)  Number of rows return: " + theaters.size());
            System.out.println("(+)  Query:\n" + sql);
        } else {
            System.out.println("\n(+)  Number of rows return: " + 0);
        }
    }

    private void logForModifyTheaterRequest(String sql, String typeRequest, int rs) {
        if(rs > 0) {
            System.out.println("\n(+)  " + typeRequest + " success!");
            System.out.println("(+)  Number of rows affected: " + rs);
            System.out.println("(+)  Query:\n" + sql);
        } else {
            System.out.println("\n(+) Fail to " + typeRequest + "!");
        }
    }

}
