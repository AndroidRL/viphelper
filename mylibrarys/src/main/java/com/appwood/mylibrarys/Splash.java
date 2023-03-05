package com.appwood.mylibrarys;

import static ProMex.classs.Utils.apiii.DEc;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ProMex.classs.Utils.Util;
import cz.msebera.android.httpclient.Header;

public class Splash extends AppCompatActivity {

    public static String extra_switch_1;
    public static String extra_switch_2;
    public static String extra_switch_3;
    public static String extra_switch_4;
    public static String extra_text_1;
    public static String extra_text_2;
    public static String extra_text_3;
    public static String extra_text_4;

    public static Context context_x;
    public static Intent intent_x;
    public static String PackageName;

    public static ArrayList<AdsModal> adsModals = new ArrayList<>();

    public static boolean isShowOpen = false;
    public static AppOpenManager appOpenManager;

    public static boolean customads_status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

    }

    /*Splash*/
    public static void next_activity_animation(String packageName, String VersonCode, Context context, Intent intent) {
        context_x = context;
        intent_x = intent;
        PackageName = packageName;
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader(DEc(Util.dojnghdklfjngkdfjng), DEc(Util.dfhdlkhmdflkhnmlkdfhm));
        asyncHttpClient.get(DEc(Util.askjdgnkjsgn) + packageName, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    /**
                     * Google
                     */
                    MyHelpers.setGoogleEnable(response.getString("enable_google_admob_id"));
                    //google Banner
                    if (response.getString("google_admob_banner_id") != null && !response.getString("google_admob_banner_id").isEmpty()) {
                        MyHelpers.SetGoogleBanner(response.getString("google_admob_banner_id"));
                    } else {
                        MyHelpers.SetGoogleBanner(null);
                    }
                    if (response.getString("google_admob_banner_id_1") != null && !response.getString("google_admob_banner_id_1").isEmpty()) {
                        MyHelpers.SetGoogleBanner1(response.getString("google_admob_banner_id_1"));
                    } else {
                        MyHelpers.SetGoogleBanner1(null);
                    }
                    if (response.getString("google_admob_banner_id_2") != null && !response.getString("google_admob_banner_id_2").isEmpty()) {
                        MyHelpers.SetGoogleBanner2(response.getString("google_admob_banner_id_2"));
                    } else {
                        MyHelpers.SetGoogleBanner2(null);
                    }
                    //google Native
                    if (response.getString("google_admob_native_id") != null && !response.getString("google_admob_native_id").isEmpty()) {
                        MyHelpers.SetGoogleNative(response.getString("google_admob_native_id"));
                    } else {
                        MyHelpers.SetGoogleNative(null);
                    }
                    if (response.getString("google_admob_native_id_1") != null && !response.getString("google_admob_native_id_1").isEmpty()) {
                        MyHelpers.SetGoogleNative1(response.getString("google_admob_native_id_1"));
                    } else {
                        MyHelpers.SetGoogleNative1(null);
                    }
                    if (response.getString("google_admob_native_id_2") != null && !response.getString("google_admob_native_id_2").isEmpty()) {
                        MyHelpers.SetGoogleNative2(response.getString("google_admob_native_id_2"));
                    } else {
                        MyHelpers.SetGoogleNative2(null);
                    }
                    //google Native btn
                    if (response.getString("google_button_name") != null && !response.getString("google_button_name").isEmpty()) {
                        MyHelpers.setGooglebutton_name(response.getString("google_button_name"));
                    } else {
                        MyHelpers.setGooglebutton_name(null);
                    }
                    if (response.getString("google_button_color") != null && !response.getString("google_button_color").isEmpty()) {
                        MyHelpers.setGooglebutton_color(response.getString("google_button_color"));
                    } else {
                        MyHelpers.setGooglebutton_color("#000000");
                    }
                    // google Open ADS
                    if (response.getString("google_open_id") != null && !response.getString("google_open_id").isEmpty()) {
                        MyHelpers.setGoogle_OpenADS(response.getString("google_open_id"));
                    } else {
                        MyHelpers.setGoogle_OpenADS(null);
                    }
                    //google Interstitial
                    if (response.getString("google_admob_interstitial_id") != null && !response.getString("google_admob_interstitial_id").isEmpty()) {
                        MyHelpers.SetGoogleInter(response.getString("google_admob_interstitial_id"));
                    } else {
                        MyHelpers.SetGoogleInter(null);
                    }
                    if (response.getString("google_admob_interstitial_id_1") != null && !response.getString("google_admob_interstitial_id_1").isEmpty()) {
                        MyHelpers.SetGoogleInter1(response.getString("google_admob_interstitial_id_1"));
                    } else {
                        MyHelpers.SetGoogleInter1(null);
                    }
                    if (response.getString("google_admob_interstitial_id_2") != null && !response.getString("google_admob_interstitial_id_2").isEmpty()) {
                        MyHelpers.SetGoogleInter2(response.getString("google_admob_interstitial_id_2"));
                    } else {
                        MyHelpers.SetGoogleInter2(null);
                    }

                    /**
                     * Facebook
                     */
                    MyHelpers.setFacebookEnable(response.getString("enable_facebook_id"));
                    //Facebook Banner
                    if (response.getString("facebook_banner_id") != null && !response.getString("facebook_banner_id").isEmpty()) {
                        MyHelpers.setFacebookBanner(response.getString("facebook_banner_id"));
                    } else {
                        MyHelpers.setFacebookBanner(null);
                    }
                    if (response.getString("facebook_banner_id_1") != null && !response.getString("facebook_banner_id_1").isEmpty()) {
                        MyHelpers.setFacebookBanner1(response.getString("facebook_banner_id_1"));
                    } else {
                        MyHelpers.setFacebookBanner1(null);
                    }
                    if (response.getString("facebook_banner_id_2") != null && !response.getString("facebook_banner_id_2").isEmpty()) {
                        MyHelpers.setFacebookBanner2(response.getString("facebook_banner_id_2"));
                    } else {
                        MyHelpers.setFacebookBanner2(null);
                    }
                    //Facebook Native
                    if (response.getString("facebook_native_id") != null && !response.getString("facebook_native_id").isEmpty()) {
                        MyHelpers.SetFacebookNative(response.getString("facebook_native_id"));
                    } else {
                        MyHelpers.SetFacebookNative(null);
                    }
                    if (response.getString("facebook_native_id_1") != null && !response.getString("facebook_native_id_1").isEmpty()) {
                        MyHelpers.SetFacebookNative1(response.getString("facebook_native_id_1"));
                    } else {
                        MyHelpers.SetFacebookNative1(null);
                    }
                    if (response.getString("facebook_native_id_2") != null && !response.getString("facebook_native_id_2").isEmpty()) {
                        MyHelpers.SetFacebookNative2(response.getString("facebook_native_id_2"));
                    } else {
                        MyHelpers.SetFacebookNative2(null);
                    }

                    //Facebook Open ADS
                    if (response.getString("facebook_open_id") != null && !response.getString("facebook_open_id").isEmpty()) {
                        MyHelpers.setfacebook_open_ad_id(response.getString("facebook_open_id"));
                    } else {
                        MyHelpers.setfacebook_open_ad_id(null);
                    }
                    //Facebook Interstitial
                    if (response.getString("facebook_interstitial_id") != null && !response.getString("facebook_interstitial_id").isEmpty()) {
                        MyHelpers.SetFacebookInter(response.getString("facebook_interstitial_id"));
                    } else {
                        MyHelpers.SetFacebookInter(null);
                    }
                    if (response.getString("facebook_interstitial_id_1") != null && !response.getString("facebook_interstitial_id_1").isEmpty()) {
                        MyHelpers.SetFacebookInter1(response.getString("facebook_interstitial_id_1"));
                    } else {
                        MyHelpers.SetFacebookInter1(null);
                    }
                    if (response.getString("facebook_interstitial_id_2") != null && !response.getString("facebook_interstitial_id_2").isEmpty()) {
                        MyHelpers.SetFacebookInter2(response.getString("facebook_interstitial_id_2"));
                    } else {
                        MyHelpers.SetFacebookInter2(null);
                    }

                    /**
                     *  Atme and qureka Link
                     */
                    //Auto Link
                    MyHelpers.setauto_link_on_off(response.getString("enable_auto_quereka_link"));  //on_off Auto link
                    if (MyHelpers.getauto_link_on_off().equals("1")) {
                        MyHelpers.setauto_link_array(response.getString("auto_quereka_link")); //link Array
                        MyHelpers.setauto_link_timer(response.getString("auto_quereka_time")); //open Timer
                        MyHelpers.Autolink();
                    }
                    /*btn link*/
                    MyHelpers.set_q_link_btn_on_off(response.getString("enable_quereka_link"));  //on_off Q link btn
                    if (MyHelpers.get_q_link_btn_on_off().equals("1")) {
                        MyHelpers.set_q_link_array(response.getString("quereka_link")); //link Array
                    }

                    /**
                     * App Lovin
                     */
                    MyHelpers.setAppLovinEnable(response.getString("enable_applovin_id"));  //on_off App Lovin
                    if (response.getString("applovin_banner") != null && !response.getString("applovin_banner").isEmpty()) {   //Banner
                        MyHelpers.setAppLovinBanner(response.getString("applovin_banner"));
                    } else {
                        MyHelpers.setAppLovinBanner(null);
                    }
                    if (response.getString("applovin_native") != null && !response.getString("applovin_native").isEmpty()) {   //Native
                        MyHelpers.setAppLovinNative(response.getString("applovin_native"));
                    } else {
                        MyHelpers.setAppLovinNative(null);
                    }
                    if (response.getString("applovin_interstitial") != null && !response.getString("applovin_interstitial").isEmpty()) {   //Inter
                        MyHelpers.setAppLovinInter(response.getString("applovin_interstitial"));

                    } else {
                        MyHelpers.setAppLovinInter(null);
                    }

                    //Back Button
                    MyHelpers.setBackAdsOnOff(response.getString("enable_back_button"));
                    if (response.getString("back_button_counter") != null && !response.getString("back_button_counter").isEmpty()) {
                        MyHelpers.setBackCounter(Integer.parseInt(response.getString("back_button_counter")));  //skip ads number
                    } else {
                        MyHelpers.setBackCounter(5000);
                    }

                    /**
                     * Skip ads
                     * 1 - Inter
                     * 2 - Native
                     * 3 - Banner
                     *
                     * btn number 0 stop ads
                     * */
                    if (response.getString("regular_button_counter") != null && !response.getString("regular_button_counter").isEmpty()) {
                        MyHelpers.setCounter_Inter(Integer.parseInt(response.getString("regular_button_counter")));
                    } else {
                        MyHelpers.setCounter_Inter(5000);
                    }
                    if (response.getString("skip_native_ad") != null && !response.getString("skip_native_ad").isEmpty()) {
                        MyHelpers.setCounter_Native(Integer.parseInt(response.getString("skip_native_ad")));
                    } else {
                        MyHelpers.setCounter_Native(5000);
                    }
                    if (response.getString("skip_banner_ad") != null && !response.getString("skip_banner_ad").isEmpty()) {
                        MyHelpers.setCounter_Banner(Integer.parseInt(response.getString("skip_banner_ad")));
                    } else {
                        MyHelpers.setCounter_Banner(5000);
                    }
                    /**
                     * MIX ads
                     * 1 - Inter
                     * 2 - Native
                     * 3 - Banner
                     * */
                    MyHelpers.setmix_ad_on_off(response.getString("mix_ad"));

                    if (response.getString("mix_ad_name") != null && !response.getString("mix_ad_name").isEmpty()) {
                        MyHelpers.setmix_ad_name(response.getString("mix_ad_name"));
                        String[] Ads_number = MyHelpers.getmix_ad_name().split(",");
                        MyHelpers.setmix_ad_banner(Ads_number[0]);
                        MyHelpers.setmix_ad_native(Ads_number[1]);
                        MyHelpers.setmix_ad_inter(Ads_number[2]);
                    } else {
                        MyHelpers.setmix_ad_name(null);
                    }

                    if (response.getString("mix_ad_counter") != null && !response.getString("mix_ad_counter").isEmpty()) {
                        MyHelpers.setmix_ad_counter(Integer.parseInt(response.getString("mix_ad_counter")));
                    } else {
                        MyHelpers.setmix_ad_counter(5000);
                    }

                    if (response.getString("mix_ad_native") != null && !response.getString("mix_ad_native").isEmpty()) {
                        MyHelpers.setmix_ad_counter_native(Integer.parseInt(response.getString("mix_ad_native")));
                    } else {
                        MyHelpers.setmix_ad_counter_native(5000);
                    }
                    if (response.getString("mix_ad_banner") != null && !response.getString("mix_ad_banner").isEmpty()) {
                        MyHelpers.setmix_ad_counter_banner(Integer.parseInt(response.getString("mix_ad_banner")));
                    } else {
                        MyHelpers.setmix_ad_counter_banner(5000);
                    }
                    /**
                     * Custom
                     */
                    MyHelpers.setCustomEnable(response.getString("custom_ads_switch"));  //on_off Custom ads
                    /**
                     * Skip Country
                     */
                    MyHelpers.setSkip_country_on_off(response.getString("off_ad_country"));
                    if (MyHelpers.getSkip_country_on_off().equals("1")) {
                        if (response.getString("off_ad_country_name") != null && !response.getString("off_ad_country_name").isEmpty()) {
                            MyHelpers.setSkip_country_list(response.getString("off_ad_country_name"));
                        } else {
                            MyHelpers.setSkip_country_list(null);
                        }
                    }

                    /**
                     * VIP Service
                     */
                    MyHelpers.setVIPService_on_off(response.getString("off_vip"));
                    if (MyHelpers.getVIPService_on_off().equals("1")) {
                        MyHelpers.setVIPService_on_country(response.getString("vip_on_country"));
                        MyHelpers.setVIPService_off_country(response.getString("vip_off_country"));
                        MyHelpers.setVIPService_ID(response.getString("vip_id_password"));
                    }

                    //Extra Data
                    extra_switch_1 = response.getString("extra_switch_1");
                    extra_switch_2 = response.getString("extra_switch_2");
                    extra_switch_3 = response.getString("extra_switch_3");
                    extra_switch_4 = response.getString("extra_switch_4");
                    extra_text_1 = response.getString("extra_text_1");
                    extra_text_2 = response.getString("extra_text_2");
                    extra_text_3 = response.getString("extra_text_3");
                    extra_text_4 = response.getString("extra_text_4");


                    //Open Other apps
                    MyHelpers.setOtherAppsShow(response.getString("replace_app"));
                    MyHelpers.setOtherAppsShowLink(response.getString("new_app_link"));
                    if (MyHelpers.getOtherAppsShow().equals("1")) {
                        MyHelpers.Entery_UpdateApps = 2;
                        context.startActivity(new Intent(context, UpdateAppActivity.class));
                        return;
                    }
                    //Update our apps
                    MyHelpers.setUpdateApps(response.getString("update_app"));
                    MyHelpers.setAppversioncode(response.getString("version_code"));
                    if (MyHelpers.getUpdateApps().equals("1")) {
                        if (!MyHelpers.getAppversioncode().equals(VersonCode)) {
                            MyHelpers.Entery_UpdateApps = 1;
                            context.startActivity(new Intent(context, UpdateAppActivity.class));
                            return;
                        }
                    }

                    //Show View
                    if (MyHelpers.getSkip_country_on_off().equals("1")) {
                        if (CheckCountry(MyHelpers.getSkip_country_list())) {
                            MyHelpers.setGoogleEnable("0");
                            MyHelpers.setFacebookEnable("0");
                            MyHelpers.setAppLovinEnable("0");
                            MyHelpers.setCustomEnable("0");
                            NextIntent(context_x, intent_x);
                        } else {
                            Custom_Call_Intent();
                        }
                    } else {
                        Custom_Call_Intent();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

            }
        });
    }

    private static void Custom_Call_Intent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (customads_status) {
                    NextADSVIP();
                } else {
                    Custom_Call_Intent();
                }
            }
        }, 1000);
    }

    private static void NextADSVIP() {
        if (MyHelpers.getVIPService_on_off().equals("1")) {
            if (CheckCountry(MyHelpers.getVIPService_off_country())) {
                BaseActivity.vpn = false;
                ShowADS();
            } else {
                List<String> DATA = new ArrayList<String>(Arrays.asList(MyHelpers.getVIPService_ID().split(",")));
                BaseActivity.id = DATA.get(0);
                BaseActivity.url = DATA.get(1);
                List<String> COUNTRY = new ArrayList<String>(Arrays.asList(MyHelpers.getVIPService_on_country().split(",")));
                BaseActivity.Country = COUNTRY.get(getRandom(0, COUNTRY.size() - 1));
                BaseActivity.vpn = true;
                BaseActivity.vpn_cancel_count = 2;
                BaseActivity.vpn_connection((Activity) context_x, new BaseActivity.vpn_callback() {
                    @Override
                    public void vpn_final_callback(String s) {
                        if (s.equals("success")) {
                            ShowADS();
                        } else {
                            ShowADS();
                        }
                    }
                });
            }
        } else {
            BaseActivity.vpn = false;
            ShowADS();
        }
    }

    private static void ShowADS() {
        if (MyHelpers.getGoogleEnable().equals("1")) {
            NextAnimationActivity.mix_adsInter = 0;
            NextAnimationActivity.mix_adsInter_back = 0;
            NextAnimationActivity.AutoGoogleInterID = 1;
            NextAnimationActivity.GoogleInterstitialAdLoad(context_x);
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                NextAnimationActivity.AutoLoadFBInterID = 1;
                NextAnimationActivity.FacebookInterLoad(context_x);
            }
            if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                NextAnimationActivity.AutoLoadFBInterID = 1;
                NextAnimationActivity.Google_failed_FacebookInterLoad(context_x);
            }
            GoogleOpenAds();

        } else if (MyHelpers.getFacebookEnable().equals("1")) {
            NextAnimationActivity.AutoLoadFBInterID = 1;
            NextAnimationActivity.mix_adsInter = 1;
            NextAnimationActivity.mix_adsInter_back = 1;
            NextAnimationActivity.FacebookInterLoad(context_x);
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                NextAnimationActivity.AutoLoadFBInterID = 1;
                NextAnimationActivity.FacebookInterLoad(context_x);
            }
            if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {
                NextAnimationActivity.AutoGoogleInterID = 1;
                NextAnimationActivity.GoogleInterstitialAdLoad(context_x);
            }
            AnimationR.Facebook_Open(context_x, intent_x);

        } else if (MyHelpers.getAppLovinEnable().equals("1")) {

            if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {
                NextAnimationActivity.AutoGoogleInterID = 1;
                NextAnimationActivity.GoogleInterstitialAdLoad(context_x);
            }
            if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                NextAnimationActivity.AutoLoadFBInterID = 1;
                NextAnimationActivity.Google_failed_FacebookInterLoad(context_x);
            }
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) context_x);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (interstitialAd.isReady()) {
                        interstitialAd.showAd();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    NextIntent(context_x, intent_x);
                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    //Fail
                    GoogleOpenAds();
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            interstitialAd.loadAd();
        } else if (MyHelpers.getCustomEnable().equals("1")) {
            CustopadsDialog();
        } else {
            NextIntent(context_x, intent_x);
        }
    }

    private static void GoogleOpenAds() {
        try {
            isShowOpen = false;
            AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                @Override
                public void OnAppOpenFailToLoad() {
                    if (isShowOpen) {
                        isShowOpen = false;
                    }
                    AnimationR.Google_open_failed_Facebook_Open(context_x, intent_x);
                }

                @Override
                public void OnAppOpenClose() {
                    if (isShowOpen) {
                        isShowOpen = false;
                    }
                    NextIntent(context_x, intent_x);
                }
            };
            isShowOpen = true;
            appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void NextIntent(Context context, Intent intent) {
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static String getCountryCode() {
        TelephonyManager tm = (TelephonyManager) context_x.getSystemService(context_x.getApplicationContext().TELEPHONY_SERVICE);
        return tm.getNetworkCountryIso();
    }

    public static Boolean CheckCountry(String Country_name) {
        try {
            List<String> COUNTRY = new ArrayList<String>(Arrays.asList(Country_name.split(",")));
            String tm = getCountryCode();
            for (int i = 0; i < COUNTRY.size(); i++) {
                if (COUNTRY.get(i).equals(tm)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getRandom(int min, int max) {
        int random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }

    public static void ShareApp(Context context, String AppName) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, AppName);
            String shareMessage = "\nInstall this cool application\n\n";
            shareMessage = shareMessage + "Check out the App at : https://play.google.com/store/apps/details?id=" + PackageName + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }

    }

    public static void RateApp(Context context) {
        try {
            Uri marketUri = Uri.parse("market://details?id=" + PackageName);
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            context.startActivity(marketIntent);
        } catch (ActivityNotFoundException e) {
            Uri marketUri = Uri.parse("https://play.google.com/store/apps/details?id=" + PackageName);
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            context.startActivity(marketIntent);
        }
    }

    public static void CustomAds() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.addHeader(DEc(Util.dojnghdklfjngkdfjng), DEc(Util.dfhdlkhmdflkhnmlkdfhm));
        asyncHttpClient.get(DEc(Util.custom) + PackageName, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject firstEvent = (JSONObject) response.get(i);
                        adsModals.add(new AdsModal(firstEvent.getString("app_name"), firstEvent.getString("enable_ads"), firstEvent.getString("ad_app_name"), firstEvent.getString("app_description"), firstEvent.getString("app_logo"), firstEvent.getString("app_banner"), firstEvent.getString("extra_switch_1"), firstEvent.getString("extra_switch_2"), firstEvent.getString("extra_switch_3"), firstEvent.getString("extra_switch_4"), firstEvent.getString("extra_text_1"), firstEvent.getString("extra_text_2"), firstEvent.getString("extra_text_3"), firstEvent.getString("extra_text_4")));
                    }
                    customads_status = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }

    public static void CustopadsDialog() {
        int ads_number = MyHelpers.getRandomNumber(0, Splash.adsModals.size() - 1);

        Dialog dialog = new Dialog(context_x);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_openads);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.findViewById(R.id.full_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    context_x.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
                } catch (ActivityNotFoundException anfe) {
                    context_x.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        dialog.findViewById(R.id.next_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NextIntent(context_x, intent_x);
            }
        });

        ImageView app_icon1 = dialog.findViewById(R.id.app_icon1);
        ImageView app_icon = dialog.findViewById(R.id.app_icon);
        ImageView ad_banner = dialog.findViewById(R.id.ad_banner);
        TextView app_name = dialog.findViewById(R.id.app_name);
        TextView app_name1 = dialog.findViewById(R.id.app_name1);
        TextView app_shot = dialog.findViewById(R.id.app_shot);

        Glide.with(context_x).load(Splash.adsModals.get(ads_number).getApp_logo()).into(app_icon1);
        Glide.with(context_x).load(Splash.adsModals.get(ads_number).getApp_logo()).into(app_icon);
        Glide.with(context_x).load(Splash.adsModals.get(ads_number).getApp_banner()).into(ad_banner);
        app_name.setText(Splash.adsModals.get(ads_number).getAd_app_name());
        app_name1.setText(Splash.adsModals.get(ads_number).getAd_app_name());
        app_shot.setText(Splash.adsModals.get(ads_number).getApp_description());

        int number  = Splash.getRandom(0,MyHelpers.color_array.size() - 1);
        try {
            LinearLayout btn_layout = (LinearLayout) dialog.findViewById(R.id.btn_layout);
            AppCompatImageView img_install = (AppCompatImageView) dialog.findViewById(R.id.img_install);
            btn_layout.setBackgroundColor(Color.parseColor(MyHelpers.color_array.get(number)));
            img_install.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyHelpers.color_array.get(number))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.show();
    }
}
