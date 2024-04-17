package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.mail.mailService;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;

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
                if(JDBC.checkBanned(studentId)){
                    JOptionPane.showMessageDialog(null,"Not allowed to leave, you are banned !");
                }else{
                    JDBC.insertLeaveLog(studentId,outTime,inTime,outDate,inDate,outstation,reason);
                    JDBC.updateInOut(studentId,"out");
                    JOptionPane.showMessageDialog(null, "Leave Started successfully");
                }
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
            if(JDBC.checkBanned(studentId)){
                JOptionPane.showMessageDialog(null,"Not allowed to leave, you are banned !");
                return;
            }
            JDBC.insertLeaveLog(studentId,outTime,inTime,outDate,inDate,outstation,reason);
            JDBC.insertOutstationLeave(studentId,1,toLoc);
            JDBC.updateInOut(studentId,"out");
            JOptionPane.showMessageDialog(null, "Leave Started Successfully");
            HashMap<String, String> emailIds= JDBC.getEmailIds(studentId);
            if (emailIds.isEmpty()) return;
            mailService.sendMailTo((String) emailIds.keySet().toArray()[0],"Gate Entry-Exit Management System","Your ward (Id: " + studentId + ") has left the campus");
            mailService.sendMailTo((String) emailIds.values().toArray()[0],"Gate Entry-Exit Management System","Student " + studentId + "has left the campus");
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
                JDBC.insertPenalty(id,log_id,100.00,inDate,"Entry on inappropriate time");
                JOptionPane.showMessageDialog(null, "Entered at invalid time, Penalty added of Rs. 100");
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
                JDBC.insertPenalty(id,log_id,500.00,inDate,"Entry without leave");
                JOptionPane.showMessageDialog(null,"No Leave log found, Pentalty added of 500Rs.");
            }
            if (!JDBC.checkApprovedOnEntry(id, inDate)){
                JDBC.updateLogTimes(log_id,inTime,inDate);
                JDBC.updateInOut(id,"in");
                JDBC.insertPenalty(id,log_id,500.00,inDate,"Entry on inappropriate time");
                JOptionPane.showMessageDialog(null, "No Approved Leaves Found, Penalty added of 500Rs, You can enter");
                HashMap<String, String> emailIds= JDBC.getEmailIds(id);
                if (emailIds.isEmpty()) return;
                mailService.sendMailTo((String) emailIds.keySet().toArray()[0],"Gate Entry-Exit Management System","Your ward (ID: " + id + ") has entered the campus");
                mailService.sendMailTo((String) emailIds.values().toArray()[0],"Gate Entry-Exit Management System","Student " + id + "has entered the campus");

                return;
            }
            JDBC.updateLogTimes(log_id,inTime,inDate);
            JDBC.updateInOut(id,"in");
            JOptionPane.showMessageDialog(null, "Entry Successful");
            HashMap<String, String> emailIds= JDBC.getEmailIds(id);
            if (emailIds.isEmpty()) return;
            mailService.sendMailTo((String) emailIds.keySet().toArray()[0],"Gate Entry-Exit Management System","Your ward (ID: " + id + ") has entered the campus");
            mailService.sendMailTo((String) emailIds.values().toArray()[0],"Gate Entry-Exit Management System","Student " + id + "has entered the campus");

        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Unable to fetch data");
        }
    }
}
