package com.example.matrimonyapp.modelclass;

public class CandidateDetails_Model {

    String id,name,age,profession_name,gender,image;

    public CandidateDetails_Model(String id, String name, String age, String profession_name, String gender,String image) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.profession_name = profession_name;
        this.gender = gender;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfession_name() {
        return profession_name;
    }

    public void setProfession_name(String profession_name) {
        this.profession_name = profession_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
