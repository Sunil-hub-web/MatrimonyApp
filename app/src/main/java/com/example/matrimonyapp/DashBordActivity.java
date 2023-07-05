package com.example.matrimonyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.matrimonyapp.fragment.PersonalInformationFrag;
import com.example.matrimonyapp.fragment.ViewProfileActivity;

public class DashBordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_bord);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ViewProfileActivity viewProfileActivity = new ViewProfileActivity();
        ft.replace(R.id.framLayout1, viewProfileActivity);
        ft.addToBackStack(null);
        ft.commit();
    }
}