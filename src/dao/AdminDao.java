package dao;

import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    public List<Admin> getAllAdmin() {
        List<Admin> admins = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM admin";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
                                                                               ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                                               ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                admins.add(mapResultSetToAdmin(rs));
            }

            String actualSql = preparedStatement.toString().split(": ")[1];
            logForAdminQuery(actualSql, rs, "Get all admin");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    public Admin getAuthenticated(String userName, String password) {
        Connection connection = JDBCConnection.getJDBCConnection();
        Admin admin = null;

        String sql = "SELECT * FROM admin " +
                     "WHERE user_name = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
                                                                               ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                                               ResultSet.CONCUR_READ_ONLY)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    admin = mapResultSetToAdmin(rs);
                }

                String actualSql = preparedStatement.toString().split(": ")[1];
                logForAdminQuery(actualSql, rs, "GetAuthenticated admin in DB");
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

        return admin;
    }

    private Admin mapResultSetToAdmin(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String userName = rs.getString("user_name");
        String password = rs.getString("password");

        return new Admin(id, userName, password);
    }

    private void logForAdminQuery(String sql, ResultSet rs, String typeRequest) throws SQLException {
        int rowResult = 0;
        if(rs == null)
            System.out.println("Number of rows returned: " + rowResult);

        rs.last();
        rowResult = rs.getRow();
        System.out.println("\n(+)  " + typeRequest + " success!");
        System.out.println("(+)  Number of rows returned: " + rowResult);
        System.out.println("(+)  Query:\n" + sql);
    }

//    private void logForGetAdminRequest(String sql, boolean rs, String typeRequest) {
//        if(!rs)
//            System.out.println("Number of rows returned: " + 0);
//
//        System.out.println("\n(+) " + typeRequest + " success!");
//        System.out.println("Number of rows returned: " + 1);
//    }
}
