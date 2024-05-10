package dao;

import model.Movie;
import model.Role;
import model.Showtime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeDao {
    public List<Showtime> getShowTimeListByMovie(int movieId) {
        List<Showtime> showtimes = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT s.* FROM showtime s " +
                "JOIN movie_showtime ms ON s.id = ms.showtime_id " +
                "JOIN movie m ON ms.movie_id = m.id " +
                "WHERE m.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
                                                                               ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                                               ResultSet.CONCUR_READ_ONLY)) {
            preparedStatement.setInt(1, movieId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                showtimes.add(mapResultSetToShowtime(rs));
            }

            String actualSql = preparedStatement.toString().split(": ")[1];
            logForShowTimeQuery(actualSql, rs, "Get show time list by Movie");

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

        return showtimes;
    }

    private void logForShowTimeQuery(String sql, ResultSet rs, String typeRequest) throws SQLException {
        int rowResult = 0;

        if(rs == null)
            System.out.println("\n(+)  Number of rows returned: " + rowResult);

        rs.last();
        rowResult = rs.getRow();
        System.out.println("\n(+)  " + typeRequest + " success!");
        System.out.println("(+)  Number of rows returned: " + rowResult);
        System.out.println("(+)  Query:\n" + sql);
    }

    private Showtime mapResultSetToShowtime(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Date showDate = rs.getDate("show_date");
        Time startTime = rs.getTime("start_time");
        Time endTime = rs.getTime("end_time");
        int seats = rs.getInt("seats");
        int available = rs.getInt("available");

        return new Showtime(id, showDate, startTime, endTime, seats, available);
    }

}
