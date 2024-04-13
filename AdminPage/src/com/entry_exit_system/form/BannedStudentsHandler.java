package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.model.PenaltyBanModel;
import com.entry_exit_system.model.PendingLeaveModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            JOptionPane.showMessageDialog(null, "Error: Unable to add data to the database.");

        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
