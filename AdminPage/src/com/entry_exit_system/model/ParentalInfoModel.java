package com.entry_exit_system.model;

public class ParentalInfoModel {
    public String id;
    public String guarddian_name;
    public String guardian_phone_number;
    public String student_name;

    public ParentalInfoModel(String id,String student_name, String guarddian_name, String guardian_phone_number) {
        this.id = id;
        this.student_name=student_name;
        this.guarddian_name = guarddian_name;
        this.guardian_phone_number = guardian_phone_number;
    }
}
