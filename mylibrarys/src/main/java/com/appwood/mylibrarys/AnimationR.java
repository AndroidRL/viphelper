package com.appwood.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AnimationR {

    /*Open Ads*/
    public static void Facebook_Open(Context context, Intent intent) {
        com.facebook.ads.InterstitialAd fb_open_ad = new com.facebook.ads.InterstitialAd(context, MyHelpers.getfacebook_open_ad_id());
        InterstitialAdListener adListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                context.startActivity(intent);
                ((Activity) context).finish();
            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                try {
                    Splash.isShowOpen = false;
                    AppOpenManager.OnAppOpenClose onAppOpenClose = new AppOpenManager.OnAppOpenClose() {
                        @Override
                        public void OnAppOpenFailToLoad() {
                            if (Splash.isShowOpen) {
                                Splash.isShowOpen = false;
                            }
                            Splash.CustopadsDialog();
                        }
                        @Override
                        public void OnAppOpenClose() {
                            if (Splash.isShowOpen) {
                                Splash.isShowOpen = false;
                            }
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                    };
                    Splash.isShowOpen = true;
                    Splash.appOpenManager = new AppOpenManager(MyHelpers.getGoogle_OpenADS(), MyHelpers.getInstant(), onAppOpenClose);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }

            @Override
            public void onAdLoaded(Ad ad) {
                try {
                    if (fb_open_ad != null) {
                        fb_open_ad.show();
                    }
                } catch (Exception e) {
                }
                NextAnimationActivity.FacebookInterLoad(context);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };
        fb_open_ad.loadAd(fb_open_ad.buildLoadAdConfig().withAdListener(adListener).build());
    }

    public static void Google_open_failed_Facebook_Open(Context context, Intent intent) {
        com.facebook.ads.InterstitialAd fb_open_ad = new com.facebook.ads.InterstitialAd(context, MyHelpers.getfacebook_open_ad_id());
        InterstitialAdListener adListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                context.startActivity(intent);
                ((Activity) context).finish();
            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                Splash.CustopadsDialog();

            }
            @Override
            public void onAdLoaded(Ad ad) {
                try {
                    if (fb_open_ad != null) {
                        fb_open_ad.show();
                    }
                } catch (Exception e) {
                }
//                FacebookInterLoad(context);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }
            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };
        fb_open_ad.loadAd(fb_open_ad.buildLoadAdConfig().withAdListener(adListener).build());
    }

}
