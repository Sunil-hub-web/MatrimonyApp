package com.example.matrimonyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences sharedprefernce;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "sharedcheckLogin";
    private static final String IS_LOGIN="islogin";
    private static final String IS_USERID="isuserid";
    private static final String IS_USERNAME="isusername";
    private static final String IS_MOBILENO="ismobileno";
    private static final String IS_USEREMAIL="isuseremail";
    private static final String IS_Password="ispassword";

    public SessionManager(Context context) {

        this.context = context;
        sharedprefernce = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedprefernce.edit();
    }

    public void setUSERID(String id){

        editor.putString(IS_USERID,id);
        editor.commit();
    }

    public String getUSERID(){

        return sharedprefernce.getString(IS_USERID,"Default");
    }

    public void setUSERNAME(String USERNAME){

        editor.putString(IS_USERNAME,USERNAME);
        editor.commit();
    }

    public String getUSERNAME(){

        return sharedprefernce.getString(IS_USERNAME,"Default");
    }

    public void setMOBILENO(String MOBILENO){

        editor.putString(IS_MOBILENO,MOBILENO);
        editor.commit();
    }

    public String getMOBILENO(){

        return sharedprefernce.getString(IS_MOBILENO,"Default");
    }
    public void setUSEREMAIL(String USEREMAIL){

        editor.putString(IS_USEREMAIL,USEREMAIL);
        editor.commit();
    }

    public String getUSEREMAIL(){

        return sharedprefernce.getString(IS_USEREMAIL,"Default");
    }

    public void setpassword(String password){

        editor.putString(IS_Password,password);
        editor.commit();
    }

    public String getPassword(){

        return sharedprefernce.getString(IS_Password,"Default");
    }

    public Boolean isLogin(){
        return sharedprefernce.getBoolean(IS_LOGIN, false);

    }
    public void setLogin(){

        editor.putBoolean(IS_LOGIN, true);
        editor.commit();

    }

    public static String getIsLogin() {
        return IS_LOGIN;
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
