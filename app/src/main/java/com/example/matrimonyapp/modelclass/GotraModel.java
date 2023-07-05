package com.example.matrimonyapp.modelclass;

public class GotraModel {

    String gotra_id,gotra_name;

    public GotraModel(String gotra_id, String gotra_name) {
        this.gotra_id = gotra_id;
        this.gotra_name = gotra_name;
    }

    public String getGotra_id() {
        return gotra_id;
    }

    public void setGotra_id(String gotra_id) {
        this.gotra_id = gotra_id;
    }

    public String getGotra_name() {
        return gotra_name;
    }

    public void setGotra_name(String gotra_name) {
        this.gotra_name = gotra_name;
    }
}
