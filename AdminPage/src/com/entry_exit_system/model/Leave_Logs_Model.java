package com.entry_exit_system.model;

public class Leave_Logs_Model {

    public String name;
    public String id;
    public String reason;
    public String out_date;
    public String out_time;
    public String in_date;
    public String in_time;


    public Leave_Logs_Model(String name, String id,String reason,String out_date,String out_time,String in_date,String in_time) {
        this.name = name;
        this.id = id;
        this.reason = reason;
        this.out_date=out_date;
        this.out_time=out_time;
        this.in_date=in_date;
        this.in_time=in_time;
    }
}
