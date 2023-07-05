package com.example.matrimonyapp.modelclass;

public class EthnicityModel {

    String ethnicity_id,ethnicity_name;

    public EthnicityModel(String ethnicity_id, String ethnicity_name) {
        this.ethnicity_id = ethnicity_id;
        this.ethnicity_name = ethnicity_name;
    }

    public String getEthnicity_id() {
        return ethnicity_id;
    }

    public void setEthnicity_id(String ethnicity_id) {
        this.ethnicity_id = ethnicity_id;
    }

    public String getEthnicity_name() {
        return ethnicity_name;
    }

    public void setEthnicity_name(String ethnicity_name) {
        this.ethnicity_name = ethnicity_name;
    }
}
