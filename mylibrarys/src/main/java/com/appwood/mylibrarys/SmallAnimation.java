package com.appwood.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;

import java.util.Objects;

public class SmallAnimation {

    public static int auto_notShow_ads_banner = 0;
    public static int mix_ads_banner = 0;

    public static AdLoader adLoader;
    private static com.google.android.gms.ads.nativead.NativeAd GoogleNativeBig = null;

    public static Context context;
    public static RelativeLayout google_banner_container;
    public static RelativeLayout fb_banner_container;
    public static View main_banner_layout;
    public static LinearLayout apploving_banner;
    public static LinearLayout custom_banner;

    /**
     * Internet Checker
     */
    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    /*Banner Main Code*/
    public static void Bottom_animation(Context context1, RelativeLayout google_banner_container1, RelativeLayout fb_banner_container1, LinearLayout apploving_banner1, LinearLayout custom_banner1, View main_banner_layout1) {
        context = context1;
        google_banner_container = google_banner_container1;
        fb_banner_container = fb_banner_container1;
        apploving_banner = apploving_banner1;
        custom_banner = custom_banner1;
        main_banner_layout = main_banner_layout1;


        if (checkConnection(context)) {
            if (MyHelpers.getCounter_Banner() == 0) {
                return;
            }
            /**
             * Skip Ads
             */
            if (MyHelpers.getCounter_Banner() != 5000) {
                auto_notShow_ads_banner++;
                if (MyHelpers.getCounter_Banner() + 1 == auto_notShow_ads_banner) {
                    auto_notShow_ads_banner = 0;
                    return;
                }
            }
            /**
             * Mix Ads
             */
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                if (MyHelpers.getmix_ad_banner().equals("0")) {
                    RegularAdsBanner();
                } else {
                    if (MyHelpers.getmix_ad_banner().length() == 2) {
                        Mix2AdsBanner(MyHelpers.getmix_ad_banner());  // 2 ads
                    } else if (MyHelpers.getmix_ad_banner().length() == 3) {
                        Mix3AdsBanner(MyHelpers.getmix_ad_banner()); // 3 ads
                    } else if (MyHelpers.getmix_ad_banner().length() == 4) {
                        Mix4AdsBanner(MyHelpers.getmix_ad_banner()); // 4 ads
                    }
                }
                return;
            }
            /**
             * Regular Ads
             */
            RegularAdsBanner();

        }
    }

    private static void RegularAdsBanner() {
        if (MyHelpers.getGoogleEnable().equals("1")) {
            GoogleNativeBanner();
        } else if (MyHelpers.getFacebookEnable().equals("1")) {
            FacebookBanner();
        } else if (MyHelpers.getAppLovinEnable().equals("1")) {
            AppLovinBannerAd();
        } else if (MyHelpers.getCustomEnable().equals("1")) {
            CustomAds();
        }
    }

    /**
     * Google Banner
     */
    private static void GoogleNativeBanner() {
        main_banner_layout.setVisibility(View.VISIBLE);
        google_banner_container.setVisibility(View.VISIBLE);
        fb_banner_container.setVisibility(View.GONE);
        apploving_banner.setVisibility(View.GONE);
        custom_banner.setVisibility(View.GONE);

        View adView = LayoutInflater.from(context).inflate(R.layout.ad_google_native_small_banner, null);
        final LinearLayout linear_ads_shows = adView.findViewById(R.id.linear_ads_shows_small_banner);
        com.google.android.gms.ads.nativead.NativeAdView adView1 = adView.findViewById(R.id.ad_view_small_banner);
        linear_ads_shows.setVisibility(View.GONE);
        adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner())
                .forNativeAd(nativeAds -> {
                    GoogleNativeBig = nativeAds;
                    main_banner_layout.setVisibility(View.VISIBLE);
                    linear_ads_shows.setVisibility(View.VISIBLE);
                    if (GoogleNativeBig != null) {
                        populateNativeBanner(GoogleNativeBig, adView1);
                    }
                    google_banner_container.removeAllViews();
                    google_banner_container.addView(adView);

                }).withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                        adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner1())
                                .forNativeAd(nativeAds -> {
                                    GoogleNativeBig = nativeAds;
                                    main_banner_layout.setVisibility(View.VISIBLE);
                                    linear_ads_shows.setVisibility(View.VISIBLE);
                                    if (GoogleNativeBig != null) {
                                        populateNativeBanner(GoogleNativeBig, adView1);
                                    }
                                    google_banner_container.removeAllViews();
                                    google_banner_container.addView(adView);

                                }).withAdListener(new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {

                                        adLoader = new AdLoader.Builder(context, MyHelpers.getGoogleBanner2())
                                                .forNativeAd(nativeAds -> {
                                                    GoogleNativeBig = nativeAds;
                                                    main_banner_layout.setVisibility(View.VISIBLE);
                                                    linear_ads_shows.setVisibility(View.VISIBLE);

                                                    if (GoogleNativeBig != null) {
                                                        populateNativeBanner(GoogleNativeBig, adView1);
                                                    }
                                                    google_banner_container.removeAllViews();
                                                    google_banner_container.addView(adView);

                                                }).withAdListener(new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                                                        CustomAds();
                                                    }

                                                    @Override
                                                    public void onAdClicked() {
                                                        super.onAdClicked();
                                                    }

                                                    @Override
                                                    public void onAdLoaded() {
                                                        super.onAdLoaded();
                                                    }

                                                    @Override
                                                    public void onAdImpression() {
                                                        super.onAdImpression();
                                                    }

                                                    @Override
                                                    public void onAdOpened() {
                                                        super.onAdOpened();
                                                    }

                                                }).build();
                                        adLoader.loadAd(new AdRequest.Builder().build());

                                    }

                                    @Override
                                    public void onAdClicked() {
                                        super.onAdClicked();
                                    }

                                    @Override
                                    public void onAdLoaded() {
                                        super.onAdLoaded();
                                    }

                                    @Override
                                    public void onAdImpression() {
                                        super.onAdImpression();
                                    }

                                    @Override
                                    public void onAdOpened() {
                                        super.onAdOpened();
                                    }

                                }).build();
                        adLoader.loadAd(new AdRequest.Builder().build());

                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public static void populateNativeBanner(com.google.android.gms.ads.nativead.NativeAd nativeAd, com.google.android.gms.ads.nativead.NativeAdView adView) {
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline_small_banner));
        adView.setBodyView(adView.findViewById(R.id.ad_body_small_banner));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action_small_banner));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon_small_banner));

        ((TextView) Objects.requireNonNull(adView.getHeadlineView())).setText(nativeAd.getHeadline());
        ((TextView) Objects.requireNonNull(adView.getBodyView())).setText(nativeAd.getBody());
        ((TextView) Objects.requireNonNull(adView.getCallToActionView())).setText(nativeAd.getCallToAction());

        if (nativeAd.getIcon() == null) {
            Objects.requireNonNull(adView.getIconView()).setVisibility(View.GONE);
        } else {
            ((ImageView) Objects.requireNonNull(adView.getIconView())).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);
    }

    /**
     * Facebook Banner
     */
    private static void FacebookBanner() {
        main_banner_layout.setVisibility(View.VISIBLE);
        google_banner_container.setVisibility(View.GONE);
        fb_banner_container.setVisibility(View.VISIBLE);
        apploving_banner.setVisibility(View.GONE);
        custom_banner.setVisibility(View.GONE);
        com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, MyHelpers.getFacebookBanner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {

                com.facebook.ads.AdView adView_2 = new com.facebook.ads.AdView(context, MyHelpers.getFacebookBanner1(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                com.facebook.ads.AdListener adListener_2 = new com.facebook.ads.AdListener() {
                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        com.facebook.ads.AdView adView_3 = new com.facebook.ads.AdView(context, MyHelpers.getFacebookBanner2(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        com.facebook.ads.AdListener adListener_3 = new com.facebook.ads.AdListener() {
                            @Override
                            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                CustomAds();
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };

                        com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView_3.buildLoadAdConfig()
                                .withAdListener(adListener_3)
                                .build();
                        adView_3.loadAd(loadAdConfig);
                        fb_banner_container.addView(adView_3);

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {

                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };

                com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView_2.buildLoadAdConfig()
                        .withAdListener(adListener_2)
                        .build();
                adView_2.loadAd(loadAdConfig);
                fb_banner_container.addView(adView_2);

            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };

        com.facebook.ads.AdView.AdViewLoadConfig loadAdConfig = adView.buildLoadAdConfig()
                .withAdListener(adListener)
                .build();
        adView.loadAd(loadAdConfig);
        fb_banner_container.addView(adView);


    }

    /**
     * App Lovin Banner
     */
    private static void AppLovinBannerAd() {
        main_banner_layout.setVisibility(View.VISIBLE);
        google_banner_container.setVisibility(View.GONE);
        fb_banner_container.setVisibility(View.GONE);
        apploving_banner.setVisibility(View.VISIBLE);
        custom_banner.setVisibility(View.GONE);
        MaxAdView adView = new MaxAdView(MyHelpers.getAppLovinBanner(), context);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int dpHeightInPx = (int) (50 * context.getResources().getDisplayMetrics().density);
        adView.setLayoutParams(new FrameLayout.LayoutParams(width, dpHeightInPx));
        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                apploving_banner.removeAllViews();
                apploving_banner.addView(adView);
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                CustomAds();
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });
        adView.loadAd();
        adView.startAutoRefresh();
    }

    /**
     * Custom Banner
     */
    private static void CustomAds() {
        main_banner_layout.setVisibility(View.VISIBLE);
        google_banner_container.setVisibility(View.GONE);
        fb_banner_container.setVisibility(View.GONE);
        apploving_banner.setVisibility(View.GONE);
        custom_banner.setVisibility(View.VISIBLE);
        int ads_number = MyHelpers.getRandomNumber(0, Splash.adsModals.size() - 1);
        LinearLayout banner_view = (LinearLayout) ((Activity) context).getLayoutInflater().inflate(R.layout.custom_banner, (ViewGroup) null);
        TextView btn_install = (TextView) banner_view.findViewById(R.id.btn_install_banner);
        RelativeLayout full_click = banner_view.findViewById(R.id.full_click_banner);
        TextView app_name = banner_view.findViewById(R.id.app_name_banner);
        TextView app_shot = banner_view.findViewById(R.id.app_shot_banner);
        ImageView app_icon = banner_view.findViewById(R.id.app_icon_banner);
        Glide.with(context).load(Splash.adsModals.get(ads_number).getApp_logo()).into(app_icon);
        app_name.setText(Splash.adsModals.get(ads_number).getAd_app_name());
        app_shot.setText(Splash.adsModals.get(ads_number).getApp_description());
        btn_install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        full_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        custom_banner.removeAllViews();
        custom_banner.addView(banner_view);
    }

    /**
     * Mix Ads
     */
    private static void Mix2AdsBanner(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        if (MyHelpers.getmix_ad_counter() != 5000) {
            mix_ads_banner++;
            if (MyHelpers.getmix_ad_counter() + 1 == mix_ads_banner) {
                MixAdsShow(second_ads);
                mix_ads_banner = 0;
            } else {
                MixAdsShow(first_ads);
            }
        } else {
            if (mix_ads_banner == 0) {
                mix_ads_banner = 1;
                MixAdsShow(first_ads);
            } else if (mix_ads_banner == 1) {
                mix_ads_banner = 0;
                MixAdsShow(second_ads);
            }
        }
    }

    private static void Mix3AdsBanner(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        if (mix_ads_banner == 0) {
            mix_ads_banner = 1;
            MixAdsShow(first_ads);
        } else if (mix_ads_banner == 1) {
            mix_ads_banner = 2;
            MixAdsShow(second_ads);
        } else if (mix_ads_banner == 2) {
            mix_ads_banner = 0;
            MixAdsShow(three_ads);
        }
    }

    private static void Mix4AdsBanner(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        if (mix_ads_banner == 0) {
            mix_ads_banner = 1;
            MixAdsShow(first_ads);
        } else if (mix_ads_banner == 1) {
            mix_ads_banner = 2;
            MixAdsShow(second_ads);
        } else if (mix_ads_banner == 2) {
            mix_ads_banner = 3;
            MixAdsShow(three_ads);
        } else if (mix_ads_banner == 3) {
            mix_ads_banner = 0;
            MixAdsShow(four_ads);

        }
    }

    private static void MixAdsShow(char ads) {
        String value = String.valueOf(ads);
        if (value.equals("g")) {
            GoogleNativeBanner();
        } else if (value.equals("f")) {
            FacebookBanner();
        } else if (value.equals("a")) {
            AppLovinBannerAd();
        } else if (value.equals("c")) {
            CustomAds();
        }
    }

}
