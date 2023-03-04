package com.appwood.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class NextAnimationActivity {
    public static com.facebook.ads.InterstitialAd interstitialAd_FB_1;
    public static int AutoGoogleInterID;
    static com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd;
    public static AdLoader adLoader;

    public static int auto_notShow_ads_inter = 0;
    public static int mix_adsInter = 0;
    public static int auto_notShow_adsBack = 0;
    public static int mix_adsInter_back = 0;
    //facebook
    public static int AutoLoadFBInterID;

    public static Activity context;
    public static Intent intent;
    public static FragmentTransaction replace_fragment;
    public static int ActivityFinish;

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

    /* Inter Code */
    public static void Slider_intents(Activity context1, Intent intent1, FragmentTransaction replace_fragment1, int ActivityFinish1) {
        /**
         * ActivityFinish == 0 next activity
         * ActivityFinish == 1 next and finish activity
         * ActivityFinish == 2 finish activity
         * ActivityFinish == 3 Fragment activity
         */
        context = context1;
        intent = intent1;
        replace_fragment = replace_fragment1;
        ActivityFinish = ActivityFinish1;

        if (checkConnection(context)) {
            if (MyHelpers.getCounter_Inter() == 0) {
                Next_Slider_intents();
                return;
            }
            /**
             * Skip Ads
             */
            if (MyHelpers.getCounter_Inter() != 5000) {
                auto_notShow_ads_inter++;
                if (MyHelpers.getCounter_Inter() + 1 == auto_notShow_ads_inter) {
                    Next_Slider_intents();
                    auto_notShow_ads_inter = 0;
                    return;
                }
            }

            /**
             * Mix Ads
             */
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                if (MyHelpers.getmix_ad_inter().equals("0")) {
                    RegularIntentAds();
                } else {
                    if (MyHelpers.getmix_ad_inter().length() == 2) {
                        Mix2Ads(MyHelpers.getmix_ad_inter());  // 2 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 3) {
                        Mix3Ads(MyHelpers.getmix_ad_inter()); // 3 ads
                    } else if (MyHelpers.getmix_ad_inter().length() == 4) {
                        Mix4Ads(MyHelpers.getmix_ad_inter()); // 4 ads
                    }
                }
                return;
            }
            /**
             * Regular Ads
             */
            RegularIntentAds();

        } else {
            Next_Slider_intents();
        }
    }

    private static void RegularIntentAds() {
        if (MyHelpers.getGoogleEnable().equals("1")) {
            googleInterShow(context, () -> {
                Next_Slider_intents();
            });
        } else if (MyHelpers.getFacebookEnable().equals("1")) {
            Next_Slider_intents();
            FacebookInterShowNext(context, new RandomAdListener() {
                @Override
                public void onClick() {
                }
            });
        } else if (MyHelpers.getAppLovinEnable().equals("1")) {
            APPLovinShow();
        } else if (MyHelpers.getCustomEnable().equals("1")) {
            CustomADSInter( );
        } else {
            Next_Slider_intents();
        }
    }

    private static void Next_Slider_intents() {
        if (ActivityFinish == 0) {
            context.startActivity(intent);
        } else if (ActivityFinish == 1) {
            context.startActivity(intent);
            context.finish();
        } else if (ActivityFinish == 2) {
            context.finish();
        } else if (ActivityFinish == 3) {
            replace_fragment.commit();
        }
    }

    private static void FacebookInterShowNext(Context context, RandomAdListener randomAdListener) {
        FacebookInterShow(context);
        randomAdListener.onClick();
    }

    /**
     * Back Inter
     */
    public static void BackAnimation(Activity context1) {
        ActivityFinish = 2;
        context = context1;
        if (checkConnection(context)) {
            if (MyHelpers.getBackAdsOnOff().equals("1")) {
                /**
                 * Ski Btn
                 */
                if (MyHelpers.getBackCounter() != 5000) {
                    auto_notShow_adsBack++;
                    if (MyHelpers.getBackCounter() + 1 == auto_notShow_adsBack) {
                        context.finish();
                        auto_notShow_adsBack = 0;
                        return;
                    }
                }
                /**
                 * Mix Ads
                 */
                if (MyHelpers.getmix_ad_on_off().equals("1")) {
                    if (MyHelpers.getmix_ad_inter().equals("0")) {
                        RegularIntentAds();
                    } else {
                        if (MyHelpers.getmix_ad_inter().length() == 2) {
                            Mix2Ads(MyHelpers.getmix_ad_inter());  // 2 ads
                        } else if (MyHelpers.getmix_ad_inter().length() == 3) {
                            Mix3Ads(MyHelpers.getmix_ad_inter()); // 3 ads
                        } else if (MyHelpers.getmix_ad_inter().length() == 4) {
                            Mix4Ads(MyHelpers.getmix_ad_inter()); // 4 ads
                        }
                    }
                    return;
                }
                RegularIntentAds();
            } else {
                context.finish();
            }
        } else {
            context.finish();
        }
    }

    public interface GetBackPointer {
        void returnAction();
    }

    /**
     * Google
     */
    /*Google Inter Load*/
    public static void GoogleInterstitialAdLoad(Context context) {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            String GOOGGLEINTEID = null;
            if (AutoGoogleInterID == 1) {
                GOOGGLEINTEID = MyHelpers.getGoogleInter();
            } else if (AutoGoogleInterID == 2) {
                GOOGGLEINTEID = MyHelpers.getGoogleInter1();
            } else if (AutoGoogleInterID == 3) {
                GOOGGLEINTEID = MyHelpers.getGoogleInter2();
            }

            mInterstitialAd.load(context, GOOGGLEINTEID, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    mInterstitialAd = interstitialAd;
                    AutoGoogleInterID = 1;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    if (AutoGoogleInterID == 1) {
                        AutoGoogleInterID = 2;
                        GoogleInterstitialAdLoad(context);
                    } else if (AutoGoogleInterID == 2) {
                        AutoGoogleInterID = 3;
                        GoogleInterstitialAdLoad(context);
                    } else if (AutoGoogleInterID == 3) {
                        //fb code
                        if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                            AutoLoadFBInterID = 1;
                            Google_failed_FacebookInterLoad(context);
                        } else {
                            CustomADSInter();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Google Inter Show*/
    private static void googleInterShow(Activity context, final GetBackPointer getBackPointer) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(context);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AutoGoogleInterID = 1;
                    GoogleInterstitialAdLoad(context);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    AutoGoogleInterID = 1;
                    GoogleInterstitialAdLoad(context);
                    if (getBackPointer != null) {
                        getBackPointer.returnAction();
                    }
                }

            });
            AutoGoogleInterID = 1;
            GoogleInterstitialAdLoad(context);

        } else {
            if (getBackPointer != null) {
                CustomADSInter( );
            }
        }
    }

    public static void Google_failed_FacebookInterLoad(Context context) {
        try {
            String FBINTER = null;
            if (AutoLoadFBInterID == 1) {
                FBINTER = MyHelpers.getFacebookInter();
            } else if (AutoLoadFBInterID == 2) {
                FBINTER = MyHelpers.getFacebookInter1();
            } else if (AutoLoadFBInterID == 3) {
                FBINTER = MyHelpers.getFacebookInter2();
            }

            interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(context, FBINTER);
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {


                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    interstitialAd_FB_1 = null;
                    if (AutoLoadFBInterID == 1) {
                        AutoLoadFBInterID = 2;
                        FacebookInterLoad(context);
                    } else if (AutoLoadFBInterID == 2) {
                        AutoLoadFBInterID = 3;
                        FacebookInterLoad(context);
                    } else {
                        CustomADSInter();
                    }

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    AutoLoadFBInterID = 1;
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(
                    interstitialAd_FB_1.buildLoadAdConfig()
                            .withAdListener(adListener)
                            .build());

        } catch (Exception e) {
        }
    }

    /**
     * Facebook
     */
    public static void FacebookInterLoad(Context context) {
        try {
            String FBINTER = null;
            if (AutoLoadFBInterID == 1) {
                FBINTER = MyHelpers.getFacebookInter();
            } else if (AutoLoadFBInterID == 2) {
                FBINTER = MyHelpers.getFacebookInter1();
            } else if (AutoLoadFBInterID == 3) {
                FBINTER = MyHelpers.getFacebookInter2();
            }

            interstitialAd_FB_1 = new com.facebook.ads.InterstitialAd(context, FBINTER);
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {


                }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    interstitialAd_FB_1 = null;
                    if (AutoLoadFBInterID == 1) {
                        AutoLoadFBInterID = 2;
                        FacebookInterLoad(context);
                    } else if (AutoLoadFBInterID == 2) {
                        AutoLoadFBInterID = 3;
                        FacebookInterLoad(context);
                    } else if (AutoLoadFBInterID == 3) {
                        if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {
                            AutoGoogleInterID = 1;
                            Facebook_failed_GoogleInterstitialAdLoad(context);
                        } else {
                            CustomADSInter();
                        }
                    }
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    AutoLoadFBInterID = 1;
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };
            interstitialAd_FB_1.loadAd(
                    interstitialAd_FB_1.buildLoadAdConfig()
                            .withAdListener(adListener)
                            .build());

        } catch (Exception e) {

        }
    }

    public static void Facebook_failed_GoogleInterstitialAdLoad(Context context) {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            String GOOGGLEINTEID = null;
            if (AutoGoogleInterID == 1) {
                GOOGGLEINTEID = MyHelpers.getGoogleInter();
            } else if (AutoGoogleInterID == 2) {
                GOOGGLEINTEID = MyHelpers.getGoogleInter1();
            } else if (AutoGoogleInterID == 3) {
                GOOGGLEINTEID = MyHelpers.getGoogleInter2();
            }

            mInterstitialAd.load(context, GOOGGLEINTEID, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    mInterstitialAd = interstitialAd;
                    AutoGoogleInterID = 1;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);

                    if (AutoGoogleInterID == 1) {
                        AutoGoogleInterID = 2;
                        GoogleInterstitialAdLoad(context);
                    } else if (AutoGoogleInterID == 2) {
                        AutoGoogleInterID = 3;
                        GoogleInterstitialAdLoad(context);
                    } else if (AutoGoogleInterID == 3) {
                        //fb code
                        if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                            AutoLoadFBInterID = 1;
                            Google_failed_FacebookInterLoad(context);
                        } else {
                            CustomADSInter();
                        }
                    } else {
                        CustomADSInter();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*FB Inter Show*/
    public static void FacebookInterShow(Context context) {
        try {
            if (interstitialAd_FB_1 != null) {
                interstitialAd_FB_1.show();
            } else {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show((Activity) context);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            AutoGoogleInterID = 1;
                            GoogleInterstitialAdLoad(context);
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            AutoGoogleInterID = 1;
                            GoogleInterstitialAdLoad(context);
                        }

                    });
                    AutoGoogleInterID = 1;
                    GoogleInterstitialAdLoad(context);

                }
            }
        } catch (Exception e) {
        }
        FacebookInterLoad(context);
    }

    /**
     * App Lovin Inter
     */
    public static void APPLovinShow() {
        MaxInterstitialAd interstitialAd = new MaxInterstitialAd(MyHelpers.getAppLovinInter(), (Activity) context);
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
                Next_Slider_intents();
            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

                if (MyHelpers.getGoogleInter() != null && !MyHelpers.getGoogleInter().isEmpty()) {
                    ApplovinFailGoogleShow();
                    return;
                }
                if (MyHelpers.getFacebookInter() != null && !MyHelpers.getFacebookInter().isEmpty()) {
                    ApplovinFailFacebookShow();
                    return;
                }
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });
        interstitialAd.loadAd();
    }

    private static void ApplovinFailFacebookShow() {
        AutoGoogleInterID = 1;
        Facebook_failed_GoogleInterstitialAdLoad(context);
    }

    private static void ApplovinFailGoogleShow() {
        AutoLoadFBInterID = 1;
        Google_failed_FacebookInterLoad(context);
    }

    /**
     * Custom
     */
    private static void CustomADSInter() {
        MyHelpers.CustomIntent = intent;
        MyHelpers.ActivityFinishs = ActivityFinish;
        if (ActivityFinish == 0) {
            context.startActivity(new Intent(context, CustomAdsInterActivity.class));
        } else if (ActivityFinish == 1) {
            context.startActivity(new Intent(context, CustomAdsInterActivity.class));
            context.finish();
        } else if (ActivityFinish == 2) {
            context.startActivity(new Intent(context, CustomAdsInterActivity.class));
            context.finish();
        } else if (ActivityFinish == 3) {
            replace_fragment.commit();
        }
    }

    /**
     * Mix Ads
     */
    private static void Mix2Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        if (MyHelpers.getmix_ad_counter() != 5000) {
            mix_adsInter++;
            if (MyHelpers.getmix_ad_counter() + 1 == mix_adsInter) {
                MixAdsShow(second_ads);
                mix_adsInter = 0;
            } else {
                MixAdsShow(first_ads);
            }
        } else {
            if (mix_adsInter == 0) {
                mix_adsInter = 1;
                MixAdsShow(first_ads);
            } else if (mix_adsInter == 1) {
                mix_adsInter = 0;
                MixAdsShow(second_ads);
            }
        }
    }

    private static void Mix3Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        if (mix_adsInter == 0) {
            mix_adsInter = 1;
            MixAdsShow(first_ads);
        } else if (mix_adsInter == 1) {
            mix_adsInter = 2;
            MixAdsShow(second_ads);
        } else if (mix_adsInter == 2) {
            mix_adsInter = 0;
            MixAdsShow(three_ads);
        }
    }

    private static void Mix4Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        if (mix_adsInter == 0) {
            mix_adsInter = 1;
            MixAdsShow(first_ads);
        } else if (mix_adsInter == 1) {
            mix_adsInter = 2;
            MixAdsShow(second_ads);
        } else if (mix_adsInter == 2) {
            mix_adsInter = 3;
            MixAdsShow(three_ads);
        } else if (mix_adsInter == 3) {
            mix_adsInter = 0;
            MixAdsShow(four_ads);

        }
    }

    private static void MixAdsShow(char ads) {
        String value = String.valueOf(ads);
        if (value.equals("g")) {
            googleInterShow(context, () -> {
                Next_Slider_intents();
            });
        } else if (value.equals("f")) {
            Next_Slider_intents();
            FacebookInterShowNext(context, new RandomAdListener() {
                @Override
                public void onClick() {

                }
            });
        } else if (value.equals("a")) {
            APPLovinShow();
        } else if (value.equals("c")) {
            CustomADSInter();
        } else {
            Next_Slider_intents();
        }
    }


}