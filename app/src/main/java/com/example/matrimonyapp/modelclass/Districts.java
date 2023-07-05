package com.example.matrimonyapp.modelclass;

import java.util.ArrayList;

public class Districts {

    String district_id,district_name;
    ArrayList<CitiesModel>citiesModels;

    public Districts(String district_id, String district_name, ArrayList<CitiesModel> citiesModels) {
        this.district_id = district_id;
        this.district_name = district_name;
        this.citiesModels = citiesModels;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public ArrayList<CitiesModel> getCitiesModels() {
        return citiesModels;
    }

    public void setCitiesModels(ArrayList<CitiesModel> citiesModels) {
        this.citiesModels = citiesModels;
    }
}
