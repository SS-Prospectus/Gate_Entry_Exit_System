package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;

import javax.swing.*;
import java.sql.SQLException;

public class LeaveLogHandler {

    public static void addLog(String studentId, String outTime, String inTime, String outDate, String inDate,boolean outstation, String reason){
        try {
            JDBC.insertLeaveLog(studentId,outTime,inTime,outDate,inDate,outstation,reason);
            JOptionPane.showMessageDialog(null, "Leave added successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to add leave");
        }
    }

}
