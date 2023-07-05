package com.example.matrimonyapp;

import static com.example.matrimonyapp.MasterdataApi.masterApiList;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.matrimonyapp.fragment.HabitDetailsFragment;
import com.example.matrimonyapp.fragment.PersonalInformationFrag;

public class UserInformation extends AppCompatActivity {

    MasterdataApi masterdataApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinform);

        masterdataApi = new MasterdataApi(UserInformation.this);
        MasterdataApi.masterApiList();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        HabitDetailsFragment habitDetailsFragment = new HabitDetailsFragment();
        ft.replace(R.id.framLayout, habitDetailsFragment);
        ft.addToBackStack(null);
        ft.commit();

    }
}