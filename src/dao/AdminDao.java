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

        Connection connection =JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM admin";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Admin admin = new Admin();

                admin.setId(rs.getInt("id"));
                admin.setUserName(rs.getString("user_name"));
                admin.setPassword(rs.getString("password"));

                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

}
