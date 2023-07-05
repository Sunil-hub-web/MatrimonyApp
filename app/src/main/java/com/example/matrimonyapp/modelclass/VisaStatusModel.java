package com.example.matrimonyapp.modelclass;

public class VisaStatusModel {

    String visastatus_id,visastatus_name;

    public VisaStatusModel(String visastatus_id, String visastatus_name) {
        this.visastatus_id = visastatus_id;
        this.visastatus_name = visastatus_name;
    }

    public String getVisastatus_id() {
        return visastatus_id;
    }

    public void setVisastatus_id(String visastatus_id) {
        this.visastatus_id = visastatus_id;
    }

    public String getVisastatus_name() {
        return visastatus_name;
    }

    public void setVisastatus_name(String visastatus_name) {
        this.visastatus_name = visastatus_name;
    }
}
