package com.example.matrimonyapp.modelclass;

public class ProfileSelected_ModelClass {

    String candidate_name,gender,profile_image,profile_id,user_id,view_id,prpackage_id,select_profile,created_date,
            updated_date,city_name,state_name,country_name;

    public ProfileSelected_ModelClass(String candidate_name, String gender, String profile_image, String profile_id,
                                      String user_id, String view_id, String prpackage_id, String select_profile,
                                      String created_date, String updated_date, String city_name, String state_name,
                                      String country_name) {

        this.candidate_name = candidate_name;
        this.gender = gender;
        this.profile_image = profile_image;
        this.profile_id = profile_id;
        this.user_id = user_id;
        this.view_id = view_id;
        this.prpackage_id = prpackage_id;
        this.select_profile = select_profile;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.city_name = city_name;
        this.state_name = state_name;
        this.country_name = country_name;
    }

    public String getCandidate_name() {
        return candidate_name;
    }

    public void setCandidate_name(String candidate_name) {
        this.candidate_name = candidate_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getView_id() {
        return view_id;
    }

    public void setView_id(String view_id) {
        this.view_id = view_id;
    }

    public String getPrpackage_id() {
        return prpackage_id;
    }

    public void setPrpackage_id(String prpackage_id) {
        this.prpackage_id = prpackage_id;
    }

    public String getSelect_profile() {
        return select_profile;
    }

    public void setSelect_profile(String select_profile) {
        this.select_profile = select_profile;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
