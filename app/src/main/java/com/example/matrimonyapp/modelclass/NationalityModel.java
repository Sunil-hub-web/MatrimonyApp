package com.example.matrimonyapp.modelclass;

public class NationalityModel {

    String nationality_id,nationality_name;

    public NationalityModel(String nationality_id, String nationality_name) {
        this.nationality_id = nationality_id;
        this.nationality_name = nationality_name;
    }

    public String getNationality_id() {
        return nationality_id;
    }

    public void setNationality_id(String nationality_id) {
        this.nationality_id = nationality_id;
    }

    public String getNationality_name() {
        return nationality_name;
    }

    public void setNationality_name(String nationality_name) {
        this.nationality_name = nationality_name;
    }

    @Override
    public String toString() {
        return "NationalityModel{" +
                "nationality_id='" + nationality_id + '\'' +
                ", nationality_name='" + nationality_name + '\'' +
                '}';
    }
}
