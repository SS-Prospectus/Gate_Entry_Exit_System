package com.entry_exit_system.jdbc;

import com.entry_exit_system.model.PendingLeaveModel;
import com.mysql.cj.jdbc.JdbcConnection;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {
    static Connection connection;

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
        String password = "suryash_sql";

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected to the database!\n");
        return connection;
    }

    public static void runTestQuery(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Student";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String id = resultSet.getString("ID");
            String name = resultSet.getString("Name");
            String inOut = resultSet.getString("campuss_in_out");
            boolean isBanned = resultSet.getBoolean("banned");

            System.out.println("ID: " + id + "\nName: " + name + "\nIn/Out: " + inOut + "\nBanned: " + isBanned + "\n");
//            System.out.println("Name: " + name + ", Banned: " + isBanned);
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

//            System.out.println("ID: " + id + "\nName: " + name + "\nIn/Out: " + inOut + "\nBanned: " + isBanned + "\n");
//            System.out.println("Name: " + name + ", Banned: " + isBanned);
        }
        return pendingLeaveList;
    }
}
