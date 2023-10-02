package com.example.matrimonyapp.modelclass;

public class ShowImageGallery_Model {

    String gallery_id,image,user_id;

    public ShowImageGallery_Model(String gallery_id, String image, String user_id) {
        this.gallery_id = gallery_id;
        this.image = image;
        this.user_id = user_id;
    }

    public String getGallery_id() {
        return gallery_id;
    }

    public void setGallery_id(String gallery_id) {
        this.gallery_id = gallery_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
