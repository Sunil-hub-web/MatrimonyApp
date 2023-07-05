package com.example.matrimonyapp.modelclass;

public class LanguageModel {

    String language_id,language_name;

    public LanguageModel(String language_id, String language_name) {
        this.language_id = language_id;
        this.language_name = language_name;
    }

    public String getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }
}
