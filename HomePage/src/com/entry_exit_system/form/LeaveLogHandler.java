package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.mail.mailService;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalTime;

public class LeaveLogHandler {
    public static void addLog(String studentId, String outTime, String inTime, String outDate, String inDate,boolean outstation, String reason){
        try {
            String outLimitStart = JDBC.getTimeLimitStart();
            LocalTime outLimStart = LocalTime.parse(outLimitStart);
            String outLimitEnd = JDBC.getTimeLimitEnd();
            LocalTime outLimEnd = LocalTime.parse(outLimitEnd);
            LocalTime outtime = LocalTime.parse(outTime);

            if(outtime.isBefore(outLimStart) || outtime.isAfter(outLimEnd)){
                JOptionPane.showMessageDialog(null,"Not allowed to leave at this time");
            }else{
                JDBC.insertLeaveLog(studentId,outTime,inTime,outDate,inDate,outstation,reason);
                JDBC.updateInOut(studentId,"out");
                JOptionPane.showMessageDialog(null, "Leave Started successfully");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to add leave");
        }
    }

    public static void addLogOutstation(String studentId, String outTime, String inTime, String outDate, String inDate,boolean outstation, String reason, String toLoc){
        try {
            int log_id = JDBC.getMostRecentLogId(studentId);
            if (!JDBC.checkApproved(studentId, outDate)){
                JOptionPane.showMessageDialog(null, "No Approved Leaves Found");
                return;
            }
            JDBC.insertLeaveLog(studentId,outTime,inTime,outDate,inDate,outstation,reason);
            mailService.sendMailTo("f20220093@pilani.bits-pilani.pilani.ac.in","Your ward left campuss","out");
            JDBC.insertOutstationLeave(studentId,1,toLoc);
            JDBC.updateInOut(studentId,"out");
            JOptionPane.showMessageDialog(null, "Leave Started Successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unable to Add Leave");
        }
    }

    public static void updateLogOnEntry(String id, String inTime, String inDate){
        int log_id = -1;
        try{
            log_id = JDBC.getMostRecentLogId(id);
            if(log_id == -1){
                JOptionPane.showMessageDialog(null,"No Leave log found");
            }
            String inLimitStart = JDBC.getTimeLimitStart();
            LocalTime inLimStart = LocalTime.parse(inLimitStart);
            String inLimitEnd = JDBC.getTimeLimitEnd();
            LocalTime inLimEnd = LocalTime.parse(inLimitEnd);
            LocalTime intime = LocalTime.parse(inTime);
            JDBC.updateLogTimes(log_id,inTime,inDate);
            JDBC.updateInOut(id,"in");

            if(intime.isBefore(inLimStart) || intime.isAfter(inLimEnd)){
                JDBC.insertPenalty(id,log_id,100.00,inDate,"Late Entry");
                JOptionPane.showMessageDialog(null, "You entered at invalid time, Penalty added of Rs. 100");
            } else {
                JOptionPane.showMessageDialog(null, "Entry Successful");
            }
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Unable to fetch data");
        }
    }

    public static void updateLogOnEntryOutStation(String id, String inTime, String inDate){
        int log_id = -1;
        try{
            log_id = JDBC.getMostRecentLogId(id);
            if(log_id == -1){
                JOptionPane.showMessageDialog(null,"No Leave log found");
            }
            JDBC.updateLogTimes(log_id,inTime,inDate);
            JDBC.updateInOut(id,"in");
            mailService.sendMailTo("f20220093@pilani.bits-pilani.pilani.ac.in","Your ward entered campuss","in");
            JOptionPane.showMessageDialog(null, "Entry Successful");

        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Unable to fetch data");
        }
    }
}
