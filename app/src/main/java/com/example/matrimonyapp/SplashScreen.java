package com.example.matrimonyapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.chootdev.blurimg.BlurImage;

public class SplashScreen extends AppCompatActivity {

    ImageView imageview;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        imageview = findViewById(R.id.imageview);

        handler = new Handler();

     //   getSupportActionBar().hide();
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        Window window = SplashScreen.this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        window.setStatusBarColor(ContextCompat.getColor(SplashScreen.this, R.color.trans));

    //    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        BlurImage.withContext(this)
                .setBlurRadius(3f)
                .setBitmapScale(0.1f)
                .blurFromResource(R.drawable.login_bg)
                .into(imageview);

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}