package com.entry_exit_system.model;

public class PendingLeaveModel {
    public String name;
    public String id;
    public String date;
    public String reason;
    public enum Status{Pending, Approved, Rejected};
    public Status status;

    public PendingLeaveModel(String name, String id, String date, String reason, Status status) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.reason = reason;
        this.status=status;
    }


}
