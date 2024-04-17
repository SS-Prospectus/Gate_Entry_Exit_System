package com.entry_exit_system.jdbc;

import com.entry_exit_system.GloablVariables;
import com.entry_exit_system.form.TimeLimits;
import com.entry_exit_system.model.*;

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

    public static void deleteOldApproved() throws SQLException {
        String sql = "DELETE FROM ApprovedLeaves WHERE DATEDIFF(CURRENT_DATE(), from_date) > 30";
        System.out.println("Old Approved Logs Deleted");
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

    }


//    public static void deleteOldLogs() throws SQLException {
//        String sql = "DELETE FROM OutStationLog WHERE DATEDIFF(CURRENT_DATE(), in_date) > 365";
//        System.out.println("Outstation Leave Logs Deleted");
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(sql);
//
//
//        sql = "DELETE FROM LeaveLogs WHERE DATEDIFF(CURRENT_DATE(), in_date) > 365";
//        System.out.println("Old Leave Logs Deleted");
//        statement = connection.createStatement();
//        statement.executeUpdate(sql);
//
//    }


    public static void deleteOldLogs() throws SQLException {

        String sql = "DELETE FROM LeaveLogs WHERE DATEDIFF(CURRENT_DATE(), in_date) > 365";
        System.out.println("Old Leave Logs Deleted");
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

    }

    public static ArrayList<PendingLeaveModel> getPendingLeavesFromDB() throws SQLException {
        String sql = "SELECT * FROM Student, ApprovedLeaves WHERE student_id=ID AND DATEDIFF(CURDATE(), from_date) < 30";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<PendingLeaveModel> pendingLeaveList= new ArrayList<>();

        while (resultSet.next()) {
            String id     = resultSet.getString("ID");
            String name   = resultSet.getString("Name");
            String from   = resultSet.getString("from_date");
            String to     = resultSet.getString("to_date");
            PendingLeaveModel pendingLeave=new PendingLeaveModel(name, id, from, to, "APPROVED");
            pendingLeaveList.add(pendingLeave);

//            System.out.println("ID: " + id + "\nName: " + name + "\nIn/Out: " + inOut + "\nBanned: " + isBanned + "\n");
//            System.out.println("Name: " + name + ", Banned: " + isBanned);
        }
        return pendingLeaveList;
    }

    public static ArrayList<OutstationRecordModel> getOutstationRecordsFromDB() throws SQLException {
        String sql = "SELECT * FROM (SELECT * FROM LeaveLogs NATURAL JOIN OutStationLog) as T1, Student WHERE ID = student_id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<OutstationRecordModel> outstationRecords= new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString("ID");
            String name = resultSet.getString("Name");
            String outDate = resultSet.getString("out_date");
            String inDate = resultSet.getString("in_date");
            String reason = resultSet.getString("reason");
            String destination = resultSet.getString("to_location");
            OutstationRecordModel outstationRecord= new OutstationRecordModel(name, id, outDate, inDate, reason, destination);
            outstationRecords.add(outstationRecord);

//            System.out.println("ID: " + id + "\nName: " + name + "\nIn/Out: " + inOut + "\nBanned: " + isBanned + "\n");
//            System.out.println("Name: " + name + ", Banned: " + isBanned);
        }
        return outstationRecords;
    }

    public static ArrayList<ParentalInfoModel> getParentalInfoFromDB() throws SQLException {
        String sql = "Select * from ParentInfo JOIN Student On Student.ID=ParentInfo.student_id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<ParentalInfoModel> ParentalInfoRecords= new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString("student_id");
            String student_name= resultSet.getString("Name");
            String name = resultSet.getString("guardian_name");
            String number = resultSet.getString("guardian_phone_number");
            ParentalInfoModel ParentInfoRecord= new ParentalInfoModel(id,student_name,name,number);
            ParentalInfoRecords.add(ParentInfoRecord);

//            System.out.println("ID: " + id + "\nName: " + name + "\nIn/Out: " + inOut + "\nBanned: " + isBanned + "\n");
//            System.out.println("Name: " + name + ", Banned: " + isBanned);
        }
        return ParentalInfoRecords;
    }

    public static ArrayList<PenaltyBanModel> getBannedStudentsFromDB() throws SQLException {
        String sql = "SELECT * FROM Bans, Student WHERE ID=student_id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<PenaltyBanModel> bannedStudentsList= new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString("ID");
            String name = resultSet.getString("Name");
            String date = resultSet.getString("date_banned");
            String reason = resultSet.getString("reason");
            String penalty_amount=resultSet.getString("Name");
            PenaltyBanModel bannedStudent = new PenaltyBanModel(name, id, date, reason,penalty_amount);
            bannedStudentsList.add(bannedStudent);


        }
        return bannedStudentsList;
    }

    public static ArrayList<PenaltyBanModel> getPenalizedStudents() throws SQLException {
        String sql = "SELECT * FROM Penalties";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<PenaltyBanModel> penalizedLeaveList= new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString("student_id");
            String name = resultSet.getString("penalty_id");
            String date = resultSet.getString("date_penalized");
            String reason = resultSet.getString("reason");
            String penalty_amount=resultSet.getString("total_penalty_amount");
            PenaltyBanModel pendingLeave=new PenaltyBanModel(name, id, date, reason,penalty_amount);
            penalizedLeaveList.add(pendingLeave);

//            System.out.println("ID: " + id + "\nName: " + name + "\nIn/Out: " + inOut + "\nBanned: " + isBanned + "\n");
//            System.out.println("Name: " + name + ", Banned: " + isBanned);
        }
        return penalizedLeaveList;
    }
    public static ArrayList<Leave_Logs_Model> getLeaveLogs() throws SQLException{
        String sql = "SELECT * FROM LeaveLogs JOIN  Student on LeaveLogs.student_id=Student.ID and is_outstation='0'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ArrayList<Leave_Logs_Model> LeaveLogsList= new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString("student_id");
            String name = resultSet.getString("Name");
            String in_date = resultSet.getString("in_date");
            String in_time = resultSet.getString("in_time");
            String out_date = resultSet.getString("out_date");
            String out_time = resultSet.getString("out_time");
            String reason = resultSet.getString("reason");
            Leave_Logs_Model Leaves=new Leave_Logs_Model(name, id, reason,out_date,out_time,in_date,in_time);
            LeaveLogsList.add(Leaves);

//            System.out.println("ID: " + id + "\nName: " + name + "\nIn/Out: " + inOut + "\nBanned: " + isBanned + "\n");
//            System.out.println("Name: " + name + ", Banned: " + isBanned);
        }
        return LeaveLogsList;
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
    public static int GetCountOfBannedStudent() throws SQLException {
        String sql = "SELECT COUNT(DISTINCT student_id) AS total_bans FROM Bans";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        int totalCount = 0;
        if (rs.next()) {
            totalCount = rs.getInt("total_bans");
        }

        // Close resources
        rs.close();
        pstmt.close();

        return totalCount;
    }

    public static int GetCountOfPenalitizedStudent() throws SQLException {
        String sql = "SELECT COUNT(DISTINCT student_id) AS total_penalties FROM Penalties";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        int totalCount = 0;
        if (rs.next()) {
            totalCount = rs.getInt("total_penalties");
        }

        // Close resources
        rs.close();
        pstmt.close();

        return totalCount;
    }

public static int GetCountOfOutStudent() throws SQLException {
    String sql = "SELECT COUNT(DISTINCT student_id) AS total_leaves FROM LeaveLogs WHERE in_date IS NULL;";
    PreparedStatement pstmt = connection.prepareStatement(sql);

    ResultSet rs = pstmt.executeQuery();
    int totalCount = 0;
    if (rs.next()) {
        totalCount = rs.getInt("total_leaves");
    }

    // Close resources
    rs.close();
    pstmt.close();

    return totalCount;
}

}