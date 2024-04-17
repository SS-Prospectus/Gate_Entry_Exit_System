package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.model.PendingLeaveModel;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PendingLeavesHandler {
    public static ArrayList<PendingLeaveModel> getPendingLeaves() {
        ArrayList<PendingLeaveModel> pendingLeaveList = null;
        try {
            pendingLeaveList = new ArrayList<>(JDBC.getPendingLeavesFromDB());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingLeaveList;
    }

    public static  void addApproved(String id, String from, String to){
        PreparedStatement pstmt = null;

        try {
            String sql = "INSERT INTO ApprovedLeaves (student_id, from_date, to_date) VALUES (?, ?, ?)";
            pstmt = JDBC.connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, from);
            pstmt.setString(3, to);

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to add data to the database. This may be due to invalid input.");

        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
