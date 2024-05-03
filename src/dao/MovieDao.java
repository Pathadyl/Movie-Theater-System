package dao;

import model.Movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    public List<Movie> getAllMovie() {
        List<Movie> movies = new ArrayList<>();

        Connection connection =JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM movie";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Movie movie = new Movie();

                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setDirector(rs.getString("director"));
                movie.setGenre(rs.getString("genre"));
                movie.setDuration(rs.getString("duration"));
                movie.setPrice(rs.getDouble("price"));
                movie.setVisibility(rs.getBoolean("visibility"));
                movie.setAvailability(rs.getBoolean("availability"));

                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }


}
