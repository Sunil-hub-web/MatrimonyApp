package com.example.matrimonyapp.modelclass;

public class RashiModel {

    String rashi_id,rashi_name;

    public RashiModel(String rashi_id, String rashi_name) {
        this.rashi_id = rashi_id;
        this.rashi_name = rashi_name;
    }

    public String getRashi_id() {
        return rashi_id;
    }

    public void setRashi_id(String rashi_id) {
        this.rashi_id = rashi_id;
    }

    public String getRashi_name() {
        return rashi_name;
    }

    public void setRashi_name(String rashi_name) {
        this.rashi_name = rashi_name;
    }
}
