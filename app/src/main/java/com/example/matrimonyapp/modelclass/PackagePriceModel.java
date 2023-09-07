package com.example.matrimonyapp.modelclass;

public class PackagePriceModel {

    String package_id,package_name,package_description,active_day,package_price,no_of_profile,package_status;

    public PackagePriceModel(String package_id, String package_name, String package_description,
                             String active_day, String package_price, String no_of_profile,
                             String package_status) {

        this.package_id = package_id;
        this.package_name = package_name;
        this.package_description = package_description;
        this.active_day = active_day;
        this.package_price = package_price;
        this.no_of_profile = no_of_profile;
        this.package_status = package_status;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_description() {
        return package_description;
    }

    public void setPackage_description(String package_description) {
        this.package_description = package_description;
    }

    public String getActive_day() {
        return active_day;
    }

    public void setActive_day(String active_day) {
        this.active_day = active_day;
    }

    public String getPackage_price() {
        return package_price;
    }

    public void setPackage_price(String package_price) {
        this.package_price = package_price;
    }

    public String getNo_of_profile() {
        return no_of_profile;
    }

    public void setNo_of_profile(String no_of_profile) {
        this.no_of_profile = no_of_profile;
    }

    public String getPackage_status() {
        return package_status;
    }

    public void setPackage_status(String package_status) {
        this.package_status = package_status;
    }
}
