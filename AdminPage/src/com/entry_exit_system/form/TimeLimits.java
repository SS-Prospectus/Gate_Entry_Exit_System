package com.entry_exit_system.form;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.entry_exit_system.jdbc.JDBC.connection;

public class TimeLimits {
    private String inTime;
    private String outTime;

    public TimeLimits(String inTime, String outTime) {
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public String getInTime() {
        return inTime;
    }

    public String getOutTime() {
        return outTime;
    }

}
