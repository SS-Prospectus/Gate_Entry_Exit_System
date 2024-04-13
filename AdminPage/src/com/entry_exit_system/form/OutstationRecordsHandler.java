package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.model.OutstationRecordModel;
import com.entry_exit_system.model.PendingLeaveModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class OutstationRecordsHandler {
    public static ArrayList<OutstationRecordModel> getOutstationRecords() {
        ArrayList<OutstationRecordModel> outstationRecords = null;
        try {
            outstationRecords = new ArrayList<>(JDBC.getOutstationRecordsFromDB());
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return outstationRecords;
    }

}
