package dao;

import model.Movie;
import model.Theater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Role;

public class MovieDao {
    public List<Movie> getAllMovies() {

        List<Movie> movies = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM movie";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while(rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                String director = rs.getString("director");
                String genre = rs.getString("genre");
                String duration = rs.getString("duration");
                double price = rs.getDouble("price");
                boolean visibility = rs.getBoolean("visibility");
                boolean availability = rs.getBoolean("availability");

                Movie movie = new Movie(title, description, director, genre,
                        duration, price, visibility, availability);

                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public List<Movie> searchMovieByTitle(String t, Role role) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM movie" +
                " WHERE LOWER(title) LIKE LOWER(?)";
        if(role == Role.CUSTOMER)
            sql += " AND visibility = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + t + "%");
            if(role == Role.CUSTOMER)
                preparedStatement.setBoolean(2, true);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                String director = rs.getString("director");
                String genre = rs.getString("genre");
                String duration = rs.getString("duration");
                double price = rs.getDouble("price");
                boolean visibility = rs.getBoolean("visibility");
                boolean availability = rs.getBoolean("availability");

                Movie movie = new Movie(title, description, director, genre,
                        duration, price, visibility, availability);

                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public List<Movie> getMovieListByTheater(Theater theater, Role role) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT m.* FROM movie m " +
                "JOIN movie_theater mt ON m.id = mt.movie_id " +
                "JOIN theater t ON mt.theater_id = t.id " +
                "WHERE t.name = ?";

        if(role == Role.CUSTOMER)
            sql += "AND m.visibility = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, theater.getName());
            if (role == Role.CUSTOMER)
                preparedStatement.setBoolean(2, true);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                String director = rs.getString("director");
                String genre = rs.getString("genre");
                String duration = rs.getString("duration");
                double price = rs.getDouble("price");
                boolean visibility = rs.getBoolean("visibility");
                boolean availability = rs.getBoolean("availability");

                Movie movie = new Movie(title, description, director, genre,
                        duration, price, visibility, availability);

                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public List<Movie> getMovieListByGenre(String g, Role role) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM movie " +
                "WHERE genre = ?";
        if (role == Role.CUSTOMER)
            sql += " AND visibility = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, g);
            if (role == Role.CUSTOMER)
                preparedStatement.setBoolean(2, true);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                String director = rs.getString("director");
                String genre = rs.getString("genre");
                String duration = rs.getString("duration");
                double price = rs.getDouble("price");
                boolean visibility = rs.getBoolean("visibility");
                boolean availability = rs.getBoolean("availability");

                Movie movie = new Movie(title, description, director, genre,
                        duration, price, visibility, availability);

                movies.add(movie);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }
    public void addMovie(String title, String description, // Add movie function
                         String director, String genre, String duration,
                         double price, boolean visibility, boolean availability){
        Connection connection =JDBCConnection.getJDBCConnection();
        String sql = "INSERT INTO movie (title, description, director, genre, duration, price, visibility, availability)" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, director);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, duration);
            preparedStatement.setDouble(6, price);
            preparedStatement.setBoolean(7, visibility);
            preparedStatement.setBoolean(8, availability);

            int rs = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMovieByID(int id){           // Delete movie by ID function
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "DELETE FROM movie WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hideMovieByID(int id){           // Set visibility of movie to 0 by ID
        Connection connection =JDBCConnection.getJDBCConnection();
        String sql = "UPDATE movie SET visibility = 0 WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editMovieByID(int id, String title, String description,                   // Edit movie function, when getting the data from UI,
                              String director, String genre, String duration,             // pass the value into this function
                              int price, boolean visibility, boolean availability){
        Connection connection =JDBCConnection.getJDBCConnection();
        String sql = "UPDATE movie " +
                     "SET title = ?, description = ?, director = ?, genre = ?, duration = ?, price = ?, visibility = ?, availability ? " +
                     "WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, director);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, duration);
            preparedStatement.setInt(6, price);
            preparedStatement.setBoolean(7, visibility);
            preparedStatement.setBoolean(8, availability);
            preparedStatement.setInt(9, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
