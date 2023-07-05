package com.example.matrimonyapp.modelclass;

import java.util.ArrayList;

public class locationData {

    String country_id,country_name;
    ArrayList<StatesModel> statesModels;

    public locationData(String country_id, String country_name, ArrayList<StatesModel> statesModels) {
        this.country_id = country_id;
        this.country_name = country_name;
        this.statesModels = statesModels;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public ArrayList<StatesModel> getStatesModels() {
        return statesModels;
    }

    public void setStatesModels(ArrayList<StatesModel> statesModels) {
        this.statesModels = statesModels;
    }
}
