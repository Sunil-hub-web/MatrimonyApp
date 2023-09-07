package com.example.matrimonyapp.modelclass;

public class PackageHistoryModel {

    String packagehistory_id,packagee_id,user_id,pack_name,pack_description,pack_activeday,pack_price,no_of_profile,
            packageactive_date,packageexpiry_date,candidate_name,candidate_id;

    public PackageHistoryModel(String packagehistory_id, String packagee_id, String user_id, String pack_name,
                               String pack_description, String pack_activeday, String pack_price,
                               String no_of_profile, String packageactive_date, String packageexpiry_date,
                               String candidate_name, String candidate_id) {

        this.packagehistory_id = packagehistory_id;
        this.packagee_id = packagee_id;
        this.user_id = user_id;
        this.pack_name = pack_name;
        this.pack_description = pack_description;
        this.pack_activeday = pack_activeday;
        this.pack_price = pack_price;
        this.no_of_profile = no_of_profile;
        this.packageactive_date = packageactive_date;
        this.packageexpiry_date = packageexpiry_date;
        this.candidate_name = candidate_name;
        this.candidate_id = candidate_id;
    }

    public String getPackagehistory_id() {
        return packagehistory_id;
    }

    public void setPackagehistory_id(String packagehistory_id) {
        this.packagehistory_id = packagehistory_id;
    }

    public String getPackagee_id() {
        return packagee_id;
    }

    public void setPackagee_id(String packagee_id) {
        this.packagee_id = packagee_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPack_name() {
        return pack_name;
    }

    public void setPack_name(String pack_name) {
        this.pack_name = pack_name;
    }

    public String getPack_description() {
        return pack_description;
    }

    public void setPack_description(String pack_description) {
        this.pack_description = pack_description;
    }

    public String getPack_activeday() {
        return pack_activeday;
    }

    public void setPack_activeday(String pack_activeday) {
        this.pack_activeday = pack_activeday;
    }

    public String getPack_price() {
        return pack_price;
    }

    public void setPack_price(String pack_price) {
        this.pack_price = pack_price;
    }

    public String getNo_of_profile() {
        return no_of_profile;
    }

    public void setNo_of_profile(String no_of_profile) {
        this.no_of_profile = no_of_profile;
    }

    public String getPackageactive_date() {
        return packageactive_date;
    }

    public void setPackageactive_date(String packageactive_date) {
        this.packageactive_date = packageactive_date;
    }

    public String getPackageexpiry_date() {
        return packageexpiry_date;
    }

    public void setPackageexpiry_date(String packageexpiry_date) {
        this.packageexpiry_date = packageexpiry_date;
    }

    public String getCandidate_name() {
        return candidate_name;
    }

    public void setCandidate_name(String candidate_name) {
        this.candidate_name = candidate_name;
    }

    public String getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(String candidate_id) {
        this.candidate_id = candidate_id;
    }
}
