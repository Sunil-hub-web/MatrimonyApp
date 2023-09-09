package com.example.matrimonyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.matrimonyapp.fragment.FillteViewProfileFragment;
import com.example.matrimonyapp.fragment.HomePageFragment;
import com.example.matrimonyapp.fragment.PersonalInformationFrag;
import com.example.matrimonyapp.fragment.ViewProfileActivity;
import com.google.android.material.navigation.NavigationView;

public class DashBordActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationview;
    DrawerLayout MyDrawer;
    MasterdataApi masterdataApi;
    TextView nav_Home,nav_Profile,nav_ChangePassword,nav_ViewedProfile,nav_SelectedProfile,nav_PackageHistory,
            nav_Logout,nav_FindMatch,nav_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_bord);

        navigationview = findViewById(R.id.navigationview);
        MyDrawer = findViewById(R.id.MyDrawer);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        HomePageFragment homePageFragment = new HomePageFragment();
        ft.replace(R.id.framLayout, homePageFragment,"HomeFragment");
        ft.addToBackStack(null);
        ft.commit();

        navigationview.setNavigationItemSelectedListener(this);
        View header = navigationview.getHeaderView(0);

        nav_Home = header.findViewById(R.id.nav_Home);
        nav_Profile = header.findViewById(R.id.nav_Profile);
        nav_ChangePassword = header.findViewById(R.id.nav_ChangePassword);
        nav_ViewedProfile = header.findViewById(R.id.nav_ViewedProfile);
        nav_SelectedProfile = header.findViewById(R.id.nav_SelectedProfile);
        nav_PackageHistory = header.findViewById(R.id.nav_PackageHistory);
        nav_Logout = header.findViewById(R.id.nav_Logout);
        nav_FindMatch = header.findViewById(R.id.nav_FindMatch);
        nav_Register = header.findViewById(R.id.nav_Register);

        nav_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDrawer.closeDrawer(GravityCompat.START);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                HomePageFragment homePageFragment = new HomePageFragment();
                ft.replace(R.id.framLayout, homePageFragment,"HomeFragment");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        nav_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDrawer.closeDrawer(GravityCompat.START);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ViewProfileActivity viewProfileActivity = new ViewProfileActivity();
                ft.replace(R.id.framLayout, viewProfileActivity,"ViewProfileActivity");
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        nav_FindMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDrawer.closeDrawer(GravityCompat.START);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                FillteViewProfileFragment fillteViewProfileFragment = new FillteViewProfileFragment();
                ft.replace(R.id.framLayout, fillteViewProfileFragment,"FillteViewProfileFragment");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        nav_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDrawer.closeDrawer(GravityCompat.START);

                masterdataApi = new MasterdataApi(DashBordActivity.this);
                MasterdataApi.masterApiList();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                PersonalInformationFrag personalInformationFrag = new PersonalInformationFrag();
                ft.replace(R.id.framLayout, personalInformationFrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        MyDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Clickmenu(View view) {

        // open drawer
        openDrawer(MyDrawer);
    }
    private static void openDrawer(DrawerLayout drawerLayout) {

        // opendrawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {

        // opendrawer layout
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}