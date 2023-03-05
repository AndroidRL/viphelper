package com.appwood.mylibrarys;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdsInterActivity extends AppCompatActivity {

    private ImageView appIcon;
    private TextView appName;
    private TextView appShot;
    private ImageView close;
    private ImageView adBanner;
    private RelativeLayout mainView;

    int ads_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_inter);
        ads_number = MyHelpers.getRandomNumber(0, Splash.adsModals.size() - 1);
        initView();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Next();
            }
        });
        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstallApps();
            }
        });
    }

    private void Next() {
        if (MyHelpers.ActivityFinishs == 2){
            finish();
            return;
        }
        startActivity(MyHelpers.CustomIntent);
        finish();
    }

    private void initView() {
        appIcon = (ImageView) findViewById(R.id.app_icon);
        adBanner = (ImageView) findViewById(R.id.ad_banner);
        appName = (TextView) findViewById(R.id.app_name);
        appShot = (TextView) findViewById(R.id.app_shot);

        appName.setText(Splash.adsModals.get(ads_number).getAd_app_name());
        appShot.setText(Splash.adsModals.get(ads_number).getApp_description());
        Glide.with(this)
                .load(Splash.adsModals.get(ads_number).getApp_logo())
                .into(appIcon);
        Glide.with(this)
                .load(Splash.adsModals.get(ads_number).getApp_banner())
                .into(adBanner);

        close = (ImageView) findViewById(R.id.close);
        mainView = (RelativeLayout) findViewById(R.id.main_view);

        int number  = Splash.getRandom(0,MyHelpers.color_array.size() - 1);
        try {
            LinearLayout  btn_layout = (LinearLayout) findViewById(R.id.btn_layout);
            AppCompatImageView  img_install = (AppCompatImageView) findViewById(R.id.img_install);
            btn_layout.setBackgroundColor(Color.parseColor(MyHelpers.color_array.get(number)));
            img_install.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyHelpers.color_array.get(number))));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onBackPressed() {

    }

    private void InstallApps() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
        } catch (ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
        }
    }
}