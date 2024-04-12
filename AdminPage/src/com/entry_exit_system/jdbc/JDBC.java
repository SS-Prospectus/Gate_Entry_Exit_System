package com.entry_exit_system.jdbc;

import com.entry_exit_system.GloablVariables;
import com.entry_exit_system.form.TimeLimits;
import com.entry_exit_system.model.List_Of_Penalized_Students_Model;
import com.entry_exit_system.model.PendingLeaveModel;

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
        String username = GloablVariables.username;
        String password = GloablVariables.password;

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
            String inOut = resultSet.getString("is_day_scholar");
            boolean isBanned = resultSet.getBoolean("banned");
            PendingLeaveModel pendingLeave=new PendingLeaveModel(name, id, "", "", PendingLeaveModel.Status.Approved);
            pendingLeaveList.add(pendingLeave);

            System.out.println("ID: " + id + "\nName: " + name + "\nIn/Out: " + inOut + "\nBanned: " + isBanned + "\n");
            System.out.println("Name: " + name + ", Banned: " + isBanned);
        }
        return pendingLeaveList;
    }

    public static ArrayList<List_Of_Penalized_Students_Model> getPenalizedStudents() throws SQLException {
        String sql = "SELECT * FROM Penalties";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<List_Of_Penalized_Students_Model> penalizedLeaveList= new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString("student_id");
            String name = resultSet.getString("penalty_id");
            String date = resultSet.getString("date_penalized");
            String reason = resultSet.getString("reason");
            List_Of_Penalized_Students_Model pendingLeave=new List_Of_Penalized_Students_Model(name, id, date, reason);
            penalizedLeaveList.add(pendingLeave);

//            System.out.println("ID: " + id + "\nName: " + name + "\nIn/Out: " + inOut + "\nBanned: " + isBanned + "\n");
//            System.out.println("Name: " + name + ", Banned: " + isBanned);
        }
        return penalizedLeaveList;
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

    public static void updateTimeLimits(String newInTime, String newOutTime) throws SQLException {
        // Define your SQL UPDATE statement
        String sql = "UPDATE TimeLimits SET in_time_limit = ?, out_time = ?";

//        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gate_entry_System", "root", "suryash_sql");
        PreparedStatement pstmt = connection.prepareStatement(sql);
            // Set parameters for the PreparedStatement
        pstmt.setString(1, newInTime);
        pstmt.setString(2, newOutTime);

            // Execute the update statement
        pstmt.executeUpdate();

    }

}
