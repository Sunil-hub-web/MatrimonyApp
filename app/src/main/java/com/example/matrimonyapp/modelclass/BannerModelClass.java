package com.example.matrimonyapp.modelclass;

public class BannerModelClass {

    String banner_id,banner_title,banner_subtitle,description,urrl,type,orderby,image;

    public BannerModelClass(String banner_id, String banner_title, String banner_subtitle,
                            String description, String urrl, String type, String orderby, String image) {

        this.banner_id = banner_id;
        this.banner_title = banner_title;
        this.banner_subtitle = banner_subtitle;
        this.description = description;
        this.urrl = urrl;
        this.type = type;
        this.orderby = orderby;
        this.image = image;
    }

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_title() {
        return banner_title;
    }

    public void setBanner_title(String banner_title) {
        this.banner_title = banner_title;
    }

    public String getBanner_subtitle() {
        return banner_subtitle;
    }

    public void setBanner_subtitle(String banner_subtitle) {
        this.banner_subtitle = banner_subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrrl() {
        return urrl;
    }

    public void setUrrl(String urrl) {
        this.urrl = urrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
