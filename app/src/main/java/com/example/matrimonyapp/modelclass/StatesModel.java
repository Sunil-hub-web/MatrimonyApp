package com.example.matrimonyapp.modelclass;

import java.util.ArrayList;

public class StatesModel {

    String state_id,state_name;
    //ArrayList<DistrictsModel> districts;

    public StatesModel(String state_id, String state_name) {
        this.state_id = state_id;
        this.state_name = state_name;
       // this.districts = districts;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

  /*  public ArrayList<DistrictsModel> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<DistrictsModel> districts) {
        this.districts = districts;
    }*/
}
