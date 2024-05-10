//package dao;
//
//import model.Bill;
//import model.Movie;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BillDao {
//
//// ==================================== Get Bill List Given Condition ==================================================
//    public List<Bill> getMemberBillList(int memberId) {
//        List<Bill> bills = new ArrayList<>();
//
//        Connection connection = JDBCConnection.getJDBCConnection();
//
//        String sql = "SELECT b.* FROM bill b " +
//                "JOIN member_bill mb ON b.id = mb.bill_id " +
//                "JOIN member m ON mb.member_id = m.id " +
//                "WHERE m.id = ?";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setInt(1, memberId);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                bills.add(mapResultSetToBill(rs));
//            }
//
//            String actualSql = preparedStatement.toString().split(": ")[1];
//            logForGetBillRequest(actualSql, bills, "Get movie list by theater");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }  finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return bills;
//    }
//
//
//// ===================================================================================================================
//    private Bill mapResultSetToBill(ResultSet rs) throws SQLException {
//        int id = rs.getInt("id");
//        int theaterId = rs.getInt("theater_id");
//        Date createdAt = rs.getDate("created_at");
//        double totalCost = rs.getDouble("total_cost");
//
//        return new Bill(id, theaterId, createdAt, totalCost);
//    }
//
//    private void logForGetBillRequest(String sql, List<Bill> bills, String typeRequest) {
//        if(!bills.isEmpty()) {
//            System.out.println("\n(+)  " + typeRequest + " success!");
//            System.out.println("(+)  Number of rows return: " + bills.size());
//            System.out.println("(+)  Query:\n" + sql);
//        } else {
//            System.out.println("\n(+)  Number of rows return: " + 0);
//        }
//    }
//}
