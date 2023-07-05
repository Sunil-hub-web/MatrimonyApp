package com.example.matrimonyapp.modelclass;

public class StarModel {

    String star_id,star_name;

    public StarModel(String star_id, String star_name) {
        this.star_id = star_id;
        this.star_name = star_name;
    }

    public String getStar_id() {
        return star_id;
    }

    public void setStar_id(String star_id) {
        this.star_id = star_id;
    }

    public String getStar_name() {
        return star_name;
    }

    public void setStar_name(String star_name) {
        this.star_name = star_name;
    }
}
