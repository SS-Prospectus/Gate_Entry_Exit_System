package com.entry_exit_system.model;

public class PenaltyBanModel {

    public String name;
    public String id;
    public String date;
    public String reason;
    public String penalty_amount;


    public PenaltyBanModel(String name, String id, String date, String reason,String penalty_amount) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.reason = reason;
        this.penalty_amount=penalty_amount;
    }
}