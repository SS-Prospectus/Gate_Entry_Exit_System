package com.entry_exit_system.model;

public class PenaltyBanModel {

    public String name;
    public String id;
    public String date;
    public String reason;


    public PenaltyBanModel(String name, String id, String date, String reason) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.reason = reason;
    }
}