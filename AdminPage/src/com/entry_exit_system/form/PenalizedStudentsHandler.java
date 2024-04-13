package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
//import com.entry_exit_system.model.List_Of_Penalized_Students_Model;
import com.entry_exit_system.model.PenaltyBanModel;
import com.entry_exit_system.model.PendingLeaveModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class PenalizedStudentsHandler {
    public static ArrayList<PenaltyBanModel> getPenalizedStudents() {
        ArrayList<PenaltyBanModel> penalizedLeaveList = null;
        try {
            penalizedLeaveList = new ArrayList<>(JDBC.getPenalizedStudents());
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return penalizedLeaveList;
    }
}
