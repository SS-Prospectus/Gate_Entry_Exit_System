package com.entry_exit_system.jdbc;

import com.entry_exit_system.GloablVariables;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    public static Connection connection;
    public static void JDBCinitialise() {
        try {
            connection = initConnection();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    public static Connection initConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Gate_entry_System";
        String username = GloablVariables.username;
        String password = GloablVariables.password;

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to the database!\n");
        return connection;
    }

    public static boolean checkStudent(String id) throws SQLException {
        String sql = "SELECT count(ID) as cnt FROM Student where ID = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt("cnt");
            return count > 0;
        }
        return false;
    }

    public static boolean checkInOut(String id) throws SQLException {
        String sql = "SELECT campus_in_out as cnt FROM Student where ID = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            String in_out = resultSet.getString("cnt");
            return in_out.equals("in");
        }
        return false;
    }

    public static void insertLeaveLog(String studentId, String outTime, String inTime, String outDate, String inDate,boolean outstation, String reason) throws SQLException {
        String sql = "INSERT INTO LeaveLogs (student_id, out_time, in_time, out_date, in_date, is_outstation, reason) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, studentId);
        pstmt.setString(2, outTime);
        pstmt.setString(3, inTime);
        pstmt.setString(4, outDate);
        pstmt.setString(5, inDate);
        pstmt.setBoolean(6, outstation); // Setting is_outstation to false
        pstmt.setString(7, reason); // Empty reason, you can change this if needed
        pstmt.executeUpdate();
    }

    public static int getMostRecentLogId(String studentId) throws SQLException {
        JDBCinitialise();
        String sql = "SELECT log_id FROM LeaveLogs WHERE student_id = ? ORDER BY log_id DESC LIMIT 1";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, studentId);
        ResultSet resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("log_id");
        }
        return -1; // Return -1 if no log found
    }

    public static void updateLogTimes(int logId, String inTime, String inDate) throws SQLException {
        String sql = "UPDATE LeaveLogs SET in_time = ?, in_date = ? WHERE log_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, inTime);
        pstmt.setString(2, inDate);
        pstmt.setInt(3, logId);
        pstmt.executeUpdate();
    }

    public static void updateInOut(String id, String inOut) throws SQLException {
        String sql = "UPDATE Student SET campus_in_out = ? WHERE Student.ID = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, inOut);
        pstmt.setString(2, id);
        pstmt.executeUpdate();
    }

    public static String getTimeLimitStart() throws SQLException {
        String sql = "SELECT in_time_limit FROM TimeLimits";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            return resultSet.getString("in_time_limit");
        }
        return null; // Return null if no result is found
    }

    public static String getTimeLimitEnd() throws SQLException {
        String sql = "SELECT out_time FROM TimeLimits";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            return resultSet.getString("out_time");
        }
        return null; // Return null if no result is found
    }

    public static void insertPenalty(String studentId, int logId, double totalPenaltyAmount, String datePenalized, String reason) throws SQLException {
        String sql = "INSERT INTO Penalties (student_id, log_id, total_penalty_amount, date_penalized, reason) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, studentId);
        pstmt.setInt(2, logId);
        pstmt.setDouble(3, totalPenaltyAmount);
        pstmt.setString(4, datePenalized);
        pstmt.setString(5, reason);
        pstmt.executeUpdate();
    }

    public static void insertOutstationLeave(String studentId, int leaveId, String toLoc) throws SQLException {
        int logId = getMostRecentLogId(studentId);
        String sql = "INSERT INTO OutStationLog (log_id, leave_id, to_location) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, logId);
        pstmt.setInt(2, leaveId);
        pstmt.setString(3, toLoc);
        pstmt.executeUpdate();
    }

}