package dao;

import model.Member;

import java.sql.*;

public class MemberDao {
    public Member getAuthenticated(String userName, String password) {
        Connection connection = JDBCConnection.getJDBCConnection();
        Member member = null;

        String sql = "SELECT * FROM member " +
                "WHERE user_name = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
                                                                               ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                                               ResultSet.CONCUR_READ_ONLY)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    member = mapResultSetToMember(rs);
                }

                String actualSql = preparedStatement.toString().split(": ")[1];
                logForMemberQuery(actualSql, rs, "GetAuthenticated admin in DB");
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

        return member;
    }

    private void logForMemberQuery(String sql, ResultSet rs, String typeRequest) throws SQLException {
        int rowResult = 0;
        if(rs == null)
            System.out.println("Number of rows returned: " + rowResult);

        rs.last();
        rowResult = rs.getRow();
        System.out.println("\n(+)  " + typeRequest + " success!");
        System.out.println("(+)  Number of rows returned: " + rowResult);
        System.out.println("(+)  Query:\n" + sql);
    }

    private Member mapResultSetToMember(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String userName = rs.getString("user_name");
        String password = rs.getString("password");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        Date date = rs.getDate("dob");
        int rewardPoint = rs.getInt("reward_point");

        return new Member(id, userName, password, name, phone, email, date, rewardPoint);
    }

}
