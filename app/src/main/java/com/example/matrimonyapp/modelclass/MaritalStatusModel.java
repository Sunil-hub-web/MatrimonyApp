package com.example.matrimonyapp.modelclass;

public class MaritalStatusModel {

    String maritalstatus_id,maritalstatus_name;

    public MaritalStatusModel(String maritalstatus_id, String maritalstatus_name) {
        this.maritalstatus_id = maritalstatus_id;
        this.maritalstatus_name = maritalstatus_name;
    }

    public String getMaritalstatus_id() {
        return maritalstatus_id;
    }

    public void setMaritalstatus_id(String maritalstatus_id) {
        this.maritalstatus_id = maritalstatus_id;
    }

    public String getMaritalstatus_name() {
        return maritalstatus_name;
    }

    public void setMaritalstatus_name(String maritalstatus_name) {
        this.maritalstatus_name = maritalstatus_name;
    }
}
