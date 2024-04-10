package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.model.PendingLeaveModel;

import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;

public class PendingLeavesHandler {
    public static ArrayList<PendingLeaveModel> getPendingLeaves() {
        ArrayList<PendingLeaveModel> pendingLeaveList = null;
        try {
            pendingLeaveList = new ArrayList<>(JDBC.getPendingLeavesFromDB());
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return pendingLeaveList;
    }
}
