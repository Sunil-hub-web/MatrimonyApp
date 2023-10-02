package com.example.matrimonyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.matrimonyapp.fragment.ChangePassword;
import com.example.matrimonyapp.fragment.FillteViewProfileFragment;
import com.example.matrimonyapp.fragment.HomePageFragment;
import com.example.matrimonyapp.fragment.PackageFragment;
import com.example.matrimonyapp.fragment.PackageHistoryFragment;
import com.example.matrimonyapp.fragment.PersonalInformationFrag;
import com.example.matrimonyapp.fragment.ProfileViewFragment;
import com.example.matrimonyapp.fragment.SelectedProfileFragment;
import com.example.matrimonyapp.fragment.ViewProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class DashBordActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationview;
    DrawerLayout MyDrawer;
    MasterdataApi masterdataApi;
    TextView nav_Home,nav_Profile,nav_ChangePassword,nav_ViewedProfile,nav_SelectedProfile,nav_PackageHistory,
            nav_Logout,nav_FindMatch,nav_Register,text_PageName;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_bord);

        navigationview = findViewById(R.id.navigationview);
        MyDrawer = findViewById(R.id.MyDrawer);
        text_PageName = findViewById(R.id.text_PageName);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        HomePageFragment homePageFragment = new HomePageFragment();
        ft.replace(R.id.framLayout, homePageFragment,"HomeFragment");
        ft.addToBackStack(null);
        ft.commit();

        navigationview.setNavigationItemSelectedListener(this);
        View header = navigationview.getHeaderView(0);

        //nav_Home = header.findViewById(R.id.nav_Home);
        //nav_Profile = header.findViewById(R.id.nav_Profile);
        nav_ChangePassword = header.findViewById(R.id.nav_ChangePassword);
        nav_ViewedProfile = header.findViewById(R.id.nav_ViewedProfile);
        nav_SelectedProfile = header.findViewById(R.id.nav_SelectedProfile);
        nav_PackageHistory = header.findViewById(R.id.nav_PackageHistory);
        nav_Logout = header.findViewById(R.id.nav_Logout);
       // nav_FindMatch = header.findViewById(R.id.nav_FindMatch);
        nav_Register = header.findViewById(R.id.nav_Register);

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

                text_PageName.setText("Personal Information");
            }
        });
        nav_ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDrawer.closeDrawer(GravityCompat.START);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ChangePassword changePassword = new ChangePassword();
                ft.replace(R.id.framLayout, changePassword,"ChangePassword");
                ft.addToBackStack(null);
                ft.commit();

                text_PageName.setText("Change Password");
            }
        });
        nav_ViewedProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDrawer.closeDrawer(GravityCompat.START);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ProfileViewFragment profileViewFragment = new ProfileViewFragment();
                ft.replace(R.id.framLayout, profileViewFragment,"ProfileViewFragment");
                ft.addToBackStack(null);
                ft.commit();

                text_PageName.setText("Viewed Profile");
            }
        });
        nav_SelectedProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDrawer.closeDrawer(GravityCompat.START);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SelectedProfileFragment selectedProfileFragment = new SelectedProfileFragment();
                ft.replace(R.id.framLayout, selectedProfileFragment,"SelectedProfileFragment");
                ft.addToBackStack(null);
                ft.commit();

                text_PageName.setText("Selected Profile");
            }
        });
        nav_PackageHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDrawer.closeDrawer(GravityCompat.START);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                PackageHistoryFragment packageHistoryFragment = new PackageHistoryFragment();
                ft.replace(R.id.framLayout, packageHistoryFragment,"PackageHistoryFragment");
                ft.addToBackStack(null);
                ft.commit();

                text_PageName.setText("Package History");
            }
        });

        bottomNavigation.setSelectedItemId(R.id.home);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()) {

                    case R.id.matches:

                        selectedFragment = new FillteViewProfileFragment();

                        text_PageName.setTextSize(18);
                        text_PageName.setText("FindMatch");

                        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, selectedFragment).addToBackStack(null).commit();

                        break;

                    case R.id.home:

                        selectedFragment = new HomePageFragment();
                        text_PageName.setTextSize(15);
                        text_PageName.setText("Home");

                        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, selectedFragment, "HomeFragment").addToBackStack(null).commit();

                        break;

                    case R.id.profile:

                        selectedFragment = new ViewProfileActivity();
                        text_PageName.setTextSize(18);
                        text_PageName.setText("Profile");

                        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, selectedFragment).addToBackStack(null).commit();

                        break;

                    case R.id.packages:

                        selectedFragment = new PackageFragment();
                        text_PageName.setTextSize(18);
                        text_PageName.setText("Package");

                        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, selectedFragment).addToBackStack(null).commit();

                        break;

                }
                //getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,selectedFragment).commit();

                return true;
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