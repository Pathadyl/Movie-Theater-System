package dao;

import model.Movie;
import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieTheaterDao {
    public List<Movie> getMovieListByTheater(int theaterId, Role role) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT m.* FROM movie m " +
                "JOIN movie_theater mt ON m.id = mt.movie_id " +
                "JOIN theater t ON mt.theater_id = t.id " +
                "WHERE t.id = ?";
        if(role == Role.CUSTOMER)
            sql += "AND m.visibility = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, theaterId);
            if (role == Role.CUSTOMER)
                preparedStatement.setBoolean(2, true);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                movies.add(mapResultSetToMovie(rs));
            }

            logForGetMovieRequest(sql, movies, "Get movie list by theater");

        } catch (SQLException e) {
            e.printStackTrace();

        }  finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return movies;
    }

    public void addMovieToTheater(int theaterId, int movieId) {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "INSERT INTO movie_theater (movie_id, theater_id)" +
                     "VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, movieId);
            preparedStatement.setInt(2, theaterId);

            int rs = preparedStatement.executeUpdate();

            logForModifyMovieRequest(sql, "Add movie to theater", rs);

        } catch (SQLException ex) {
            ex.printStackTrace();

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

    public void removeMovieFromTheater(int theaterId, int movieId) {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "DELETE FROM movie_theater " +
                "WHERE movie_id = ? AND theater_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, movieId);
            preparedStatement.setInt(2, theaterId);

            int rs = preparedStatement.executeUpdate();

            logForModifyMovieRequest(sql, "Delete movie from theater", rs);

        } catch (SQLException ex) {
            ex.printStackTrace();

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

    private Movie mapResultSetToMovie(ResultSet rs) throws SQLException {
        int id = rs.getInt("title");
        String title = rs.getString("title");
        String description = rs.getString("description");
        String director = rs.getString("director");
        String genre = rs.getString("genre");
        String duration = rs.getString("duration");
        double price = rs.getDouble("price");
        boolean visibility = rs.getBoolean("visibility");
        boolean availability = rs.getBoolean("availability");

        return new Movie(id, title, description, director, genre, duration, price, visibility, availability);
    }

    private void logForGetMovieRequest(String sql, List<Movie> movies, String typeRequest) {
        if(!movies.isEmpty()) {
            System.out.println("\n(+)  " + typeRequest + " success!");
            System.out.println("(+)  Number of rows return: " + movies.size());
            System.out.println("(+)  Query:\n" + sql);
        } else {
            System.out.println("\n(+)  Number of rows return: " + 0);
        }
    }

    private void logForModifyMovieRequest(String sql, String typeRequest, int rs) {
        if(rs > 0) {
            System.out.println("\n(+)  " + typeRequest + " success!");
            System.out.println("(+)  Number of rows affected: " + rs);
            System.out.println("(+)  Query:\n" + sql);
        } else {
            System.out.println("\n(+) Fail to " + typeRequest + "!");
        }
    }
}
