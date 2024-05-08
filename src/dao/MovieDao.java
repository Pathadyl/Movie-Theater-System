package dao;

import model.Role;
import model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM movie";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while(rs.next()) {
                movies.add(mapResultSetToMovie(rs));
            }

            logForGetMovieRequest(sql, movies, "Get all movies");

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

        return movies;
    }

    public List<Movie> searchMovieByTitle(String t, Role role) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM movie" +
                     " WHERE LOWER(title) LIKE LOWER(?)";
        if(role == Role.CUSTOMER)
            sql += " AND visibility = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + t + "%");
            if(role == Role.CUSTOMER)
                preparedStatement.setBoolean(2, true);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                movies.add(mapResultSetToMovie(rs));
            }

            logForGetMovieRequest(sql, movies, "Get movie by title");

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

        return movies;
    }

    public List<Movie> getMovieListByGenre(String g, Role role) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM movie " +
                "WHERE genre = ?";
        if (role == Role.CUSTOMER)
            sql += " AND visibility = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, g);
            if (role == Role.CUSTOMER)
                preparedStatement.setBoolean(2, true);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                movies.add(mapResultSetToMovie(rs));
            }

            logForGetMovieRequest(sql, movies, "Get movie by genre");

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


        return movies;
    }

    public boolean addMovieToDB(String title, String description, // Add movie function
                             String director, String genre, String duration,
                             double price, boolean visibility, boolean availability) {
        boolean flag = false;

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "INSERT INTO movie (title, description, director, genre, duration, price, visibility, availability)" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, director);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, duration);
            preparedStatement.setDouble(6, price);
            preparedStatement.setBoolean(7, visibility);
            preparedStatement.setBoolean(8, availability);

            int rs = preparedStatement.executeUpdate();

            logForModifyMovieRequest(sql, "Add movie to database", rs);

            if (rs > 0)
                flag = true;

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

        return flag;
    }

    public boolean deleteMovieFromDB(int id) {           // Delete movie by ID function
        boolean flag = false;

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "DELETE FROM movie WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            int rs = preparedStatement.executeUpdate();

            logForModifyMovieRequest(sql, "Delete movie from database", rs);

            if (rs > 0)
                flag = true;

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

        return flag;
    }

    public boolean hideMovie(int id) {           // Set visibility of movie to 0 by ID
        boolean flag = false;

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "UPDATE movie SET visibility = 0 WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            int rs = preparedStatement.executeUpdate();

            logForModifyMovieRequest(sql, "Hide movie from customer", rs);

            if (rs > 0) flag = true;

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

        return flag;
    }

    public void editMovieFromDB(int id, String title, String description,                   // Edit movie function, when getting the data from UI,
                                String director, String genre, String duration,             // pass the value into this function
                                double price, boolean visibility, boolean availability) {
        Connection connection =JDBCConnection.getJDBCConnection();

        String sql = "UPDATE movie " +
                     "SET title = ?, description = ?, director = ?, genre = ?, duration = ?, price = ?, visibility = ?, availability ? " +
                     "WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, director);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, duration);
            preparedStatement.setDouble(6, price);
            preparedStatement.setBoolean(7, visibility);
            preparedStatement.setBoolean(8, availability);
            preparedStatement.setInt(9, id);

            int rs = preparedStatement.executeUpdate();

            logForModifyMovieRequest(sql, "Edit movie from database", rs);

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


    private Movie mapResultSetToMovie(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
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
