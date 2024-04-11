package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;
import com.entry_exit_system.model.List_Of_Penalized_Students_Model;
import com.entry_exit_system.model.PendingLeaveModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class PenalizedStudentsHandler {
    public static ArrayList<List_Of_Penalized_Students_Model> getPenalizedStudents() {
        ArrayList<List_Of_Penalized_Students_Model> penalizedLeaveList = null;
        try {
            penalizedLeaveList = new ArrayList<>(JDBC.getPenalizedStudents());
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return penalizedLeaveList;
    }
}
