package com.example.matrimonyapp.modelclass;

public class ProfessionModel {

    String profession_id,profession_name;

    public ProfessionModel(String profession_id, String profession_name) {
        this.profession_id = profession_id;
        this.profession_name = profession_name;
    }

    public String getProfession_id() {
        return profession_id;
    }

    public void setProfession_id(String profession_id) {
        this.profession_id = profession_id;
    }

    public String getProfession_name() {
        return profession_name;
    }

    public void setProfession_name(String profession_name) {
        this.profession_name = profession_name;
    }
}
