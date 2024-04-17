package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.model.PenaltyBanModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BannedStudentsHandler {
    public static ArrayList<PenaltyBanModel> getBannedStudents() {
        ArrayList<PenaltyBanModel> bannedStudentsList = null;
        try {
            bannedStudentsList = new ArrayList<>(JDBC.getBannedStudentsFromDB());
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return bannedStudentsList;
    }

    public static void removeBan(String id) {
        PreparedStatement pstmt = null;

        try {
            String sql = "DELETE FROM Bans WHERE student_id = ?";
            pstmt = JDBC.connection.prepareStatement(sql);
            pstmt.setString(1, id);

            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Data deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No data found to delete for student ID: " + id);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to delete data from the database.");

        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void addBan(String id, String reason){
        PreparedStatement pstmt = null;
        Date date = Date.valueOf(LocalDate.now());

        try {
            String sql = "INSERT INTO Bans (student_id, date_banned, reason) VALUES (?, ?, ?)";
            pstmt = JDBC.connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, String.valueOf(date));
            pstmt.setString(3, reason);

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
