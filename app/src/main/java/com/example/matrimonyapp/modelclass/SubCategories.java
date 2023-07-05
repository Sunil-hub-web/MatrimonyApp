package com.example.matrimonyapp.modelclass;

public class SubCategories {

    String cat_id,cat_name,parent_id;

    public SubCategories(String cat_id, String cat_name,String parent_id) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.parent_id = parent_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
