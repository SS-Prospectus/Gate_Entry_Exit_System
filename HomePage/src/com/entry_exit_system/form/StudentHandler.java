package com.entry_exit_system.form;

import com.entry_exit_system.jdbc.JDBC;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentHandler {
    public static boolean studentExist(String id){
        boolean present = false;
        try {
            present = JDBC.checkStudent(id);
            if (present) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No Student found with given id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return present;
    }

    public static boolean studentInCampus(String id){
        boolean present = false;
        try {
            present = JDBC.checkInOut(id);
            return present;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return present;
    }
}
