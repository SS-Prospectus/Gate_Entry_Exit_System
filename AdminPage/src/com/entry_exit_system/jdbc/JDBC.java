package com.entry_exit_system.jdbc;

import com.entry_exit_system.form.TimeLimits;
import com.entry_exit_system.model.PendingLeaveModel;
import com.mysql.cj.jdbc.JdbcConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    public static Connection connection;

    public static void JDBCinitialise() {

        try {
            connection = initConnection();
            try {
                runTestQuery(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

    }

    public static Connection initConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Gate_entry_System";
        String username = "root";
        String password = "root@123";

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to the database!\n");
        return connection;
    }

    public static void runTestQuery(Connection connection) throws SQLException {
        String sql = "SELECT in_time_limit, out_time FROM TimeLimits";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String inTime = resultSet.getString("in_time_limit");
            String outTime = resultSet.getString("out_time");

            System.out.println("In Time: " + inTime + "\nOut Time: " + outTime + "\n");
        }

    }

    public static ArrayList<PendingLeaveModel> getPendingLeavesFromDB() throws SQLException {
        String sql = "SELECT * FROM Student";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<PendingLeaveModel> pendingLeaveList= new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString("ID");
            String name = resultSet.getString("Name");
            String inOut = resultSet.getString("campuss_in_out");
            boolean isBanned = resultSet.getBoolean("banned");
            PendingLeaveModel pendingLeave=new PendingLeaveModel(name, id, "", "", PendingLeaveModel.Status.Approved);
            pendingLeaveList.add(pendingLeave);

            System.out.println("ID: " + id + "\nName: " + name + "\nIn/Out: " + inOut + "\nBanned: " + isBanned + "\n");
            System.out.println("Name: " + name + ", Banned: " + isBanned);
        }
        return pendingLeaveList;
    }
    public static List<TimeLimits> runTest(Connection connection) throws SQLException {
        String sql = "SELECT in_time_limit, out_time FROM TimeLimits";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<TimeLimits> timeLimitsList = new ArrayList<>();

        while (resultSet.next()) {
            String inTime = resultSet.getString("in_time_limit");
            String outTime = resultSet.getString("out_time");

            TimeLimits timeLimits = new TimeLimits(inTime, outTime);
            timeLimitsList.add(timeLimits);

            System.out.println("In Time: " + inTime + "\nOut Time: " + outTime + "\n");
        }

        return timeLimitsList;
    }
    public static void updateInTime(String newInTime) throws SQLException {
        String sql = "UPDATE TimeLimits SET in_time_limit = ?"; // SQL query to update in_time
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newInTime);

        // Execute the update query
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("In Time updated successfully!");
        } else {
            System.out.println("Failed to update In Time!");
        }
    }
    public static void updateOutTime(String newOutTime) throws SQLException {
        String sql = "UPDATE TimeLimits SET out_time = ?"; // SQL query to update out_time
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newOutTime);

        // Execute the update query
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Out Time updated successfully!");
        } else {
            System.out.println("Failed to update Out Time!");
        }
    }
    public static void updateTimeLimits(String newInTime, String newOutTime) throws SQLException {
        // Define your SQL UPDATE statement
        String sql = "UPDATE TimeLimits SET in_time_limit = ?, out_time = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gate_entry_System", "root", "root@123");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set parameters for the PreparedStatement
            pstmt.setString(1, newInTime);
            pstmt.setString(2, newOutTime);

            // Execute the update statement
            pstmt.executeUpdate();
        }
    }

}
