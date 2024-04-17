package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.model.Leave_Logs_Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class LeaveLogsHandler {
    public static ArrayList<Leave_Logs_Model> getLeaveLogs() {
        ArrayList<Leave_Logs_Model> LeaveLogsList = null;
        try {
            LeaveLogsList = new ArrayList<>(JDBC.getLeaveLogs());
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return LeaveLogsList;
    }


}
