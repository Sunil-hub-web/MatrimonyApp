package com.example.matrimonyapp.modelclass;

public class EducationModel {

    String education_id,education_name;

    public EducationModel(String education_id, String education_name) {
        this.education_id = education_id;
        this.education_name = education_name;
    }

    public String getEducation_id() {
        return education_id;
    }

    public void setEducation_id(String education_id) {
        this.education_id = education_id;
    }

    public String getEducation_name() {
        return education_name;
    }

    public void setEducation_name(String education_name) {
        this.education_name = education_name;
    }
}
