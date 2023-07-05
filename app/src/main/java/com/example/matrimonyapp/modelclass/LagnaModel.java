package com.example.matrimonyapp.modelclass;

public class LagnaModel {

    String lagna_id,lagna_name;

    public LagnaModel(String lagna_id, String lagna_name) {
        this.lagna_id = lagna_id;
        this.lagna_name = lagna_name;
    }

    public String getLagna_id() {
        return lagna_id;
    }

    public void setLagna_id(String lagna_id) {
        this.lagna_id = lagna_id;
    }

    public String getLagna_name() {
        return lagna_name;
    }

    public void setLagna_name(String lagna_name) {
        this.lagna_name = lagna_name;
    }
}
