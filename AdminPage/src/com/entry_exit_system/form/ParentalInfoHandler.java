package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.model.ParentalInfoModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class ParentalInfoHandler {
    public static ArrayList<ParentalInfoModel> getParentalInfoRecords() {
        ArrayList<ParentalInfoModel> ParentalInfoRecords = null;
        try {
            ParentalInfoRecords = new ArrayList<>(JDBC.getParentalInfoFromDB());
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return ParentalInfoRecords;
    }
}
