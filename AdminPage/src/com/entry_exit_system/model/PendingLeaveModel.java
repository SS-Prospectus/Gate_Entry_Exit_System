package com.entry_exit_system.model;

public class PendingLeaveModel {
    public String name;
    public String id;
    public String fromDate;
    public String toDate;
    public enum Status{Pending, Approved, Rejected};
    public String status;

    public PendingLeaveModel(String name, String id, String fromDate, String toDate, String status) {
        this.name = name;
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status=status;
    }


}
