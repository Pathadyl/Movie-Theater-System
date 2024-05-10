package dao;

import model.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestDao {
    public Guest createGuest() {
        Guest guest = null;

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "INSERT INTO guest (name, phone, email)" +
                     "VALUES (null, null, null)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
                                                              PreparedStatement.RETURN_GENERATED_KEYS)) {
            int rs = preparedStatement.executeUpdate();

            if (rs > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        guest = new Guest(generatedId, null, null,
                                          null, null, null);
                    }

                    String actualSql = preparedStatement.toString().split(": ")[1];
                    logForGuestQuery(actualSql, rs, "Create new guest");

                }
            }

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

        return guest;
    }

    private Guest mapResultSetToGuest(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String email = rs.getString("email");

        return new Guest(id, "null", "null", name, phone, email);
    }

    private void logForGuestQuery(String sql, ResultSet rs, String typeRequest) throws SQLException {
        int rowResult = 0;

        if(rs == null)
            System.out.println("\n(+)  Number of rows returned: " + rowResult);

        rs.last();
        rowResult = rs.getRow();
        System.out.println("\n(+)  " + typeRequest + " success!");
        System.out.println("(+)  Number of rows returned: " + rowResult);
        System.out.println("(+)  Query:\n" + sql);
    }

    private void logForGuestQuery(String sql, int rs, String typeRequest) {
        if(rs <= 0)
            System.out.println("\n(+) Fail to " + typeRequest + "!");

        System.out.println("\n(+)  " + typeRequest + " success!");
        System.out.println("(+)  Number of rows affected: " + rs);
        System.out.println("(+)  Query:\n" + sql);
    }

}

