package com.entry_exit_system.model;
//OutstationRecordModel outstationRecord= new OutstationRecordModel(name, id, outDate, inDate, reason, destination);
public class OutstationRecordModel {

    public String name;

    public String id;
    public String outDate;
    public String inDate;
    public String reason;
    public String destination;


    public OutstationRecordModel(String name, String id,String outDate,String inDate,String reason,String destination) {
        this.name = name;
        this.id = id;
        this.outDate = outDate;
        this.inDate=inDate;
        this.destination=destination;
        this.reason = reason;
    }
}
