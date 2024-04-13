package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;

import javax.swing.*;
import java.sql.SQLException;

public class LeaveLogHandler {

    public static void addLog(String studentId, String outTime, String inTime, String outDate, String inDate,boolean outstation, String reason){
        try {
            JDBC.insertLeaveLog(studentId,outTime,inTime,outDate,inDate,outstation,reason);
            JDBC.updateInOut(studentId,"out");
            JOptionPane.showMessageDialog(null, "Leave added successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to add leave");
        }
    }

    public static void updateLogOnEntry(String id, String inTime, String inDate){
        int log_id = -1;
        try{
            log_id = JDBC.getMostRecentLogId(id);
            if(log_id == -1){
                JOptionPane.showMessageDialog(null,"No Leave log found");
            }
            JDBC.updateLogTimes(log_id,inTime,inDate);
            JDBC.updateInOut(id,"in");
            JOptionPane.showMessageDialog(null, "Entry Sucdessful");
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Unable to fetch data");
        }
    }

}
