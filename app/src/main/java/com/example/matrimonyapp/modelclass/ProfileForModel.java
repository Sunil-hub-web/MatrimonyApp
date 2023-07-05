package com.example.matrimonyapp.modelclass;

public class ProfileForModel {

    String profilefor_id,profilefor_name;

    public String getProfilefor_id() {
        return profilefor_id;
    }

    public void setProfilefor_id(String profilefor_id) {
        this.profilefor_id = profilefor_id;
    }

    public String getProfilefor_name() {
        return profilefor_name;
    }

    public void setProfilefor_name(String profilefor_name) {
        this.profilefor_name = profilefor_name;
    }

    public ProfileForModel(String profilefor_id, String profilefor_name) {
        this.profilefor_id = profilefor_id;
        this.profilefor_name = profilefor_name;
    }
}
