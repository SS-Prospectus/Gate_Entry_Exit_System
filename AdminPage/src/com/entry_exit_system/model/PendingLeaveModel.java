package com.entry_exit_system.model;

public class PendingLeaveModel {
    public String name;
    public String id;
    public String fromDate;
    public String toDate;
    public String reason;
    public enum Status{Pending, Approved, Rejected};
    public String status;

    public PendingLeaveModel(String name, String id, String fromDate, String toDate, String reason, String status) {
        this.name = name;
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.status=status;
    }


}
