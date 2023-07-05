package com.example.matrimonyapp.modelclass;

public class BodyType {

    String bodytype_id,bodytype_name;

    public BodyType(String bodytype_id, String bodytype_name) {
        this.bodytype_id = bodytype_id;
        this.bodytype_name = bodytype_name;
    }

    public String getBodytype_id() {
        return bodytype_id;
    }

    public void setBodytype_id(String bodytype_id) {
        this.bodytype_id = bodytype_id;
    }

    public String getBodytype_name() {
        return bodytype_name;
    }

    public void setBodytype_name(String bodytype_name) {
        this.bodytype_name = bodytype_name;
    }
}
