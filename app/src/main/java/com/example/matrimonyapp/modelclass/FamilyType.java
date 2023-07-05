package com.example.matrimonyapp.modelclass;

public class FamilyType {

    String familystatus_id,familystatus_name;

    public FamilyType(String familystatus_id, String familystatus_name) {
        this.familystatus_id = familystatus_id;
        this.familystatus_name = familystatus_name;
    }

    public String getFamilystatus_id() {
        return familystatus_id;
    }

    public void setFamilystatus_id(String familystatus_id) {
        this.familystatus_id = familystatus_id;
    }

    public String getFamilystatus_name() {
        return familystatus_name;
    }

    public void setFamilystatus_name(String familystatus_name) {
        this.familystatus_name = familystatus_name;
    }
}
