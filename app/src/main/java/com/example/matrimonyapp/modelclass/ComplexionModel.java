package com.example.matrimonyapp.modelclass;

public class ComplexionModel {

    String complexion_id,comlexion_name;

    public ComplexionModel(String complexion_id, String comlexion_name) {
        this.complexion_id = complexion_id;
        this.comlexion_name = comlexion_name;
    }

    public String getComplexion_id() {
        return complexion_id;
    }

    public void setComplexion_id(String complexion_id) {
        this.complexion_id = complexion_id;
    }

    public String getComlexion_name() {
        return comlexion_name;
    }

    public void setComlexion_name(String comlexion_name) {
        this.comlexion_name = comlexion_name;
    }
}
