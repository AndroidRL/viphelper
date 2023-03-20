package com.appwood.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.facebook.ads.Ad;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAdsShowOptions;

public class NextAnimation {

    /*Google*/
    public static com.google.android.gms.ads.interstitial.InterstitialAd google_InterstitialAd;
    public static com.google.android.gms.ads.interstitial.InterstitialAd google_InterstitialAd_1;
    public static com.google.android.gms.ads.interstitial.InterstitialAd google_InterstitialAd_2;
    public static com.google.android.gms.ads.interstitial.InterstitialAd google_InterstitialAd_3;
    public static int inter_show_id = 0;
    public static int AutoGoogleInterID;
    public static int mix_adsInter = 0;
    public static int auto_notShow_ads_inter = 0;

    //facebook
    public static com.facebook.ads.InterstitialAd facebook_interstitialAd;
    public static int AutoLoadFBInterID;

    //App Loving
    public static MaxInterstitialAd applovin_interstitialAd;

    //Unity
    public static boolean UnityAdLoadChecker = false;

    /*Helper*/
    public static Activity main_context;
    public static int auto_notShow_adsBack = 0;


    /**
     * INTERNET CHECK CODE
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

    /**
     * INTERSTITIAL ADS CODE START
     */
    public static void SliderAnimation(Activity context) {
        main_context = context;
        if (checkConnection(context)) {
            /*Stop Ads*/
            if (MyProHelperClass.getCounter_Inter() == 0) {
                return;
            }

            /*Skip Ads*/
            if (MyProHelperClass.getCounter_Inter() != 5000) {
                auto_notShow_ads_inter++;
                if (MyProHelperClass.getCounter_Inter() + 1 == auto_notShow_ads_inter) {
                    auto_notShow_ads_inter = 0;
                    return;
                }
            }

            /*Mix and Regular ads*/
            if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
                MixAds();
            } else {
                RegularADS();
            }
        } else {
            CustomADSInter();
        }
    }


    /**
     * Regular Ads
     */
    private static void RegularADS() {
        if (MyProHelperClass.getGoogleEnable().equals("1") && MyProHelperClass.getlive_status().equals("1")) {
            GoogleADSShow("r");
        } else if (MyProHelperClass.getFacebookEnable().equals("1") && MyProHelperClass.getlive_status().equals("1")) {
            FacebookADSShow();
        } else if (MyProHelperClass.getAppLovinEnable().equals("1")) {
            RegularAppLovingShow();
        } else if (MyProHelperClass.getUnityEnable().equals("1")) {
            UnityADSShow();
        } else if (MyProHelperClass.get_q_link_btn_on_off().equals("1")) {
            MyProHelperClass.BtnAutolink();
        } else if (MyProHelperClass.getCustomEnable().equals("1")) {
            CustomADSInter();
        }
    }

    /**
     * Back Btn Interstitial
     */
    public static void BackAnimation(Activity context) {
        main_context = context;
        if (checkConnection(context)) {
            if (MyProHelperClass.getBackAdsOnOff().equals("1")) {
                /**
                 * Skip Ads
                 */
                if (MyProHelperClass.getBackCounter() != 5000) {
                    auto_notShow_adsBack++;
                    if (MyProHelperClass.getBackCounter() + 1 == auto_notShow_adsBack) {
                        context.finish();
                        auto_notShow_adsBack = 0;
                        return;
                    }
                }
                /**
                 * Mix Ads
                 */
                if (MyProHelperClass.getmix_ad_on_off().equals("1")) {
                    MixAds();
                } else {
                    RegularADS();
                }
            } else {
                context.finish();
            }
        } else {
            context.finish();
        }
    }


    /**
     * Ads Show
     */
    /*Google Inter Show*/
    private static void GoogleADSShow(String adview) {
        if (MyProHelperClass.Google_inter_number == 1) {
            GoogleInterShow(adview);
        } else if (MyProHelperClass.Google_inter_number == 2) {
            if (inter_show_id == 0) {
                inter_show_id = 1;
                googleInterShow1(adview);
            } else {
                inter_show_id = 0;
                googleInterShow2(adview);
            }
        } else if (MyProHelperClass.Google_inter_number == 3) {
            if (inter_show_id == 0) {
                inter_show_id = 1;
                googleInterShow1(adview);
            } else if (inter_show_id == 1) {
                inter_show_id = 2;
                googleInterShow2(adview);
            } else {
                inter_show_id = 0;
                googleInterShow3(adview);
            }
        }
    }

    private static void GoogleInterShow(String adview) {
        if (google_InterstitialAd != null) {
            google_InterstitialAd.show(main_context);
            google_InterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AllGoogle_Fails_OtherAdShow(adview);
                 }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                }

            });
        } else {
            AllGoogle_Fails_OtherAdShow(adview);
         }
        AutoGoogleInterID = 1;
        AllAdsPreLoadsInter("g");

    }

    private static void Google_Fails_Facebook_AppLoving_Unity_Show() {
        try {
            if (facebook_interstitialAd != null) {
                facebook_interstitialAd.show();
            } else {
                Google_Facebook_Fails_AppLoving_Unity_Show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AllAdsPreLoadsInter("f");
    }

    private static void Google_Facebook_Fails_AppLoving_Unity_Show() {
        try {
            if (applovin_interstitialAd.isReady()) {
                applovin_interstitialAd.showAd();
            } else {
                AllAds_Fails_Unity_Show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AllAdsPreLoadsInter("a");

    }

    /*Google Inter Show 1 ID*/
    private static void googleInterShow1(String adview) {
        if (google_InterstitialAd_1 != null) {
            google_InterstitialAd_1.show(main_context);
            google_InterstitialAd_1.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AllGoogle_Fails_OtherAdShow(adview);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                 }
            });
        } else {
            AllGoogle_Fails_OtherAdShow(adview);
         }
        AllAdsPreLoadsInter("g1");

    }

    /*Google Inter Show 2 ID*/
    private static void googleInterShow2(String adview) {
        if (google_InterstitialAd_2 != null) {
            google_InterstitialAd_2.show(main_context);
            google_InterstitialAd_2.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AllGoogle_Fails_OtherAdShow(adview);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();

                }
            });
        } else {
            AllGoogle_Fails_OtherAdShow(adview);
         }

        AllAdsPreLoadsInter("g2");

    }

    /*Google Inter Show 3 ID*/
    private static void googleInterShow3(String adview) {
        if (google_InterstitialAd_3 != null) {
            google_InterstitialAd_3.show(main_context);
            google_InterstitialAd_3.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    AllGoogle_Fails_OtherAdShow(adview);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                 }
            });
        } else {
            AllGoogle_Fails_OtherAdShow(adview);
         }
        AllAdsPreLoadsInter("g3");

    }

    private static void AllAds_Fails_Unity_Show() {
        if (UnityAdLoadChecker) {
            UnityAds.show((Activity) main_context, MyProHelperClass.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                    CustomADSInter();
                    UnityAdLoadChecker = false;
                    AllAdsPreLoadsInter("u");
                }

                @Override
                public void onUnityAdsShowStart(String placementId) {


                }

                @Override
                public void onUnityAdsShowClick(String placementId) {

                }

                @Override
                public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                    UnityAdLoadChecker = false;
                    AllAdsPreLoadsInter("u");
                }
            });
        } else {
            CustomADSInter();
        }
    }

    private static void AllGoogle_Fails_OtherAdShow(String adview) {
        if (adview.equals("r")) {
            Google_Fails_Facebook_AppLoving_Unity_Show();
        } else if (adview.equals("f")) {
            Facebook_Fails_RegularAppLovingShow();
        } else if (adview.equals("a")) {
            AppLoving_Google_ShowFails_Facebook_Show_Lisner();
        } else if (adview.equals("u")) {
            Unity_Google_ShowFails_Facebook_ShowLisner();
        }
    }

    /*Facebook Inter Show*/
    private static void FacebookADSShow() {
        if (facebook_interstitialAd != null && facebook_interstitialAd.isAdLoaded()) {
            facebook_interstitialAd.show();
        } else {
            GoogleADSShow("f");
        }
        AllAdsPreLoadsInter("f");
    }

    private static void Facebook_Fails_RegularAppLovingShow() {
        try {
            if (applovin_interstitialAd.isReady()) {
                applovin_interstitialAd.showAd();
            } else {
                AllAds_Fails_Unity_Show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AllAdsPreLoadsInter("a");

    }

    /*AppLoving Inter Show*/
    private static void RegularAppLovingShow() {
        try {
            if (applovin_interstitialAd.isReady()) {
                applovin_interstitialAd.showAd();
            } else {
                GoogleADSShow("a");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AllAdsPreLoadsInter("a");

    }

    private static void AppLoving_Google_ShowFails_Facebook_Show_Lisner() {
        if (facebook_interstitialAd != null) {
            facebook_interstitialAd.show();
        } else {
            AllAds_Fails_Unity_Show();
        }
        AllAdsPreLoadsInter("f");

    }

    /*Unity Inter Show*/
    private static void UnityADSShow() {
        if (UnityAdLoadChecker) {
            UnityAds.show((Activity) main_context, MyProHelperClass.getUnityInterID(), new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
                    GoogleADSShow("u");
                    AllAdsPreLoadsInter("u");

                }

                @Override
                public void onUnityAdsShowStart(String placementId) {


                }

                @Override
                public void onUnityAdsShowClick(String placementId) {

                }

                @Override
                public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
                     AllAdsPreLoadsInter("u");
                }
            });
        } else {
            GoogleADSShow("u");
         }

    }

    private static void Unity_Google_ShowFails_Facebook_ShowLisner() {
        if (facebook_interstitialAd != null) {
            facebook_interstitialAd.show();
        } else {
            Unity_Google_Facebook_ShowFails_ApplovinShowLisner();
        }
        AllAdsPreLoadsInter("f");
    }

    private static void Unity_Google_Facebook_ShowFails_ApplovinShowLisner() {
        try {
            if (applovin_interstitialAd.isReady()) {
                applovin_interstitialAd.showAd();
            } else {
                CustomADSInter();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AllAdsPreLoadsInter("a");
    }

    /*Custom Inter Show*/
    private static void CustomADSInter() {
        if (Splash.adsViewModals.size() != 0) {
            main_context.startActivity(new Intent(main_context, CustomAdsActivity.class));
        }
    }

    /*Open Link*/
    public static void OpenLink() {
        if (MyProHelperClass.get_q_link_array() != null && !MyProHelperClass.get_q_link_array().isEmpty()) {
            MyProHelperClass.BtnAutolink();
        }
    }

    /**
     * Mix Ads
     */
    /*Helper*/
    private static void MixAds() {
        if (MyProHelperClass.getmix_ad_inter().length() != 0) {
            if (MyProHelperClass.getmix_ad_inter().length() == 1) {
                Mix1Ads(MyProHelperClass.getmix_ad_inter()); //1 ads
            } else if (MyProHelperClass.getmix_ad_inter().length() == 2) {
                Mix2Ads(MyProHelperClass.getmix_ad_inter());  // 2 ads
            } else{
                MixUnlimitedAdsInter(MyProHelperClass.getmix_ad_inter()); // Unlimited
            }
        }
    }

    private static void Mix1Ads(String s) {
        MixAdsShow(String.valueOf(s.charAt(0)));
    }

    private static void Mix2Ads(String s) {
        if (MyProHelperClass.getmix_ad_counter_inter() != 5000) {
            mix_adsInter++;
            if (MyProHelperClass.getmix_ad_counter_inter() + 1 == mix_adsInter) {
                MixAdsShow(String.valueOf(s.charAt(1)));
                mix_adsInter = 0;
            } else {
                MixAdsShow(String.valueOf(s.charAt(0)));
            }
        } else {
            if (mix_adsInter == 0) {
                mix_adsInter = 1;
                MixAdsShow(String.valueOf(s.charAt(0)));
            } else if (mix_adsInter == 1) {
                mix_adsInter = 0;
                MixAdsShow(String.valueOf(s.charAt(1)));
            }
        }
    }

    private static void MixUnlimitedAdsInter(String s) {
        MixAdsShow(String.valueOf(s.charAt(mix_adsInter)));
        if (MyProHelperClass.getmix_ad_inter().length() - 1 == mix_adsInter) {
            mix_adsInter = 0;
        } else {
            mix_adsInter++;
        }
    }

    private static void MixAdsShow(String value) {
        if (value.equals("g") && MyProHelperClass.getlive_status().equals("1")) {
            GoogleADSShow("r");
        } else if (value.equals("f") && MyProHelperClass.getlive_status().equals("1")) {
            FacebookADSShow();
        } else if (value.equals("a")) {
            RegularAppLovingShow();
        } else if (value.equals("u")) {
            UnityADSShow();
        } else if (value.equals("q")) {
            MyProHelperClass.BtnAutolink();
        } else if (value.equals("c")) {
            CustomADSInter();
        }
    }

    /**
     * PreLoad
     */
    /*Google*/
    public static void GoogleInterPreload() {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            String GOOGGLEINTEID = null;
            if (AutoGoogleInterID == 1) {
                GOOGGLEINTEID = MyProHelperClass.getGoogleInter();
            } else if (AutoGoogleInterID == 2) {
                GOOGGLEINTEID = MyProHelperClass.getGoogleInter1();
            } else if (AutoGoogleInterID == 3) {
                GOOGGLEINTEID = MyProHelperClass.getGoogleInter2();
            }
            google_InterstitialAd.load(main_context, GOOGGLEINTEID, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    google_InterstitialAd = interstitialAd;
                    AutoGoogleInterID = 1;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    if (AutoGoogleInterID == 1) {
                        AutoGoogleInterID = 2;
                        GoogleInterPreload();
                    } else if (AutoGoogleInterID == 2) {
                        AutoGoogleInterID = 3;
                        GoogleInterPreload();
                    } else {
                        google_InterstitialAd = null;
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GoogleInterPreload1() {

        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            google_InterstitialAd_1.load(main_context, MyProHelperClass.getGoogleInter(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    google_InterstitialAd_1 = interstitialAd;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    google_InterstitialAd_1 = null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void GoogleInterPreload2() {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            google_InterstitialAd_2.load(main_context, MyProHelperClass.getGoogleInter1(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    google_InterstitialAd_2 = interstitialAd;

                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    google_InterstitialAd_2 = null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void GoogleInterPreload3() {
        try {
            AdRequest adRequest = new AdRequest.Builder().build();
            google_InterstitialAd_3.load(main_context, MyProHelperClass.getGoogleInter2(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    super.onAdLoaded(interstitialAd);
                    google_InterstitialAd_3 = interstitialAd;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    google_InterstitialAd_3 = null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Facebook*/
    public static void FacebookInterPreLoad() {
        try {
            String FBINTER = null;
            if (AutoLoadFBInterID == 1) {
                FBINTER = MyProHelperClass.getFacebookInter();
            } else if (AutoLoadFBInterID == 2) {
                FBINTER = MyProHelperClass.getFacebookInter1();
            } else if (AutoLoadFBInterID == 3) {
                FBINTER = MyProHelperClass.getFacebookInter2();
            }
            facebook_interstitialAd = new com.facebook.ads.InterstitialAd(main_context, FBINTER);
            InterstitialAdListener adListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                 }

                @Override
                public void onError(Ad ad, com.facebook.ads.AdError adError) {
                    if (AutoLoadFBInterID == 1) {
                        AutoLoadFBInterID = 2;
                        FacebookInterPreLoad();
                    } else if (AutoLoadFBInterID == 2) {
                        AutoLoadFBInterID = 3;
                        FacebookInterPreLoad();
                    } else {
                        facebook_interstitialAd = null;
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
            facebook_interstitialAd.loadAd(facebook_interstitialAd.buildLoadAdConfig().withAdListener(adListener).build());
        } catch (Exception e) {
        }
    }

    /*AppLoving*/
    public static void AppLovingInterPreLoad() {
        applovin_interstitialAd = new MaxInterstitialAd(MyProHelperClass.getAppLovinInter(), (Activity) main_context);
        applovin_interstitialAd.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {

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
                applovin_interstitialAd = null;

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });
        applovin_interstitialAd.loadAd();
    }

    /*Unity*/
    public static void UnityInterPreLoad() {
        UnityAds.load(MyProHelperClass.getUnityInterID(), new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                UnityAdLoadChecker = true;
             }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
                UnityAdLoadChecker = false;
             }
        });
    }

    /*All Preload*/
    public static void AllAdsPreLoadsInter(String refresh) {
        if (refresh.equals("g")) {
            google_InterstitialAd = null;
        } else if (refresh.equals("g1")) {
            google_InterstitialAd_1 = null;
        } else if (refresh.equals("g2")) {
            google_InterstitialAd_2 = null;
        } else if (refresh.equals("g3")) {
            google_InterstitialAd_3 = null;
        } else if (refresh.equals("f")) {
            facebook_interstitialAd = null;
        } else if (refresh.equals("a")) {
            applovin_interstitialAd = null;
        } else if (refresh.equals("u")) {
            UnityAdLoadChecker = false;
        }

        /*Google*/
        if (MyProHelperClass.Google_inter_number == 1) {
            if (MyProHelperClass.getGoogleInter() != null && !MyProHelperClass.getGoogleInter().isEmpty() && MyProHelperClass.getlive_status().equals("1")) {
                if (google_InterstitialAd == null) {
                    GoogleInterPreload();
                }
            }
        } else if (MyProHelperClass.Google_inter_number == 2) {
            if (MyProHelperClass.getGoogleInter() != null && !MyProHelperClass.getGoogleInter().isEmpty() && MyProHelperClass.getlive_status().equals("1")) {
                if (google_InterstitialAd_1 == null) {
                    GoogleInterPreload1();
                }

            }
            if (MyProHelperClass.getGoogleInter1() != null && !MyProHelperClass.getGoogleInter1().isEmpty() && MyProHelperClass.getlive_status().equals("1")) {
                if (google_InterstitialAd_2 == null) {
                    GoogleInterPreload2();
                }

            }
        } else if (MyProHelperClass.Google_inter_number == 3) {
            if (MyProHelperClass.getGoogleInter() != null && !MyProHelperClass.getGoogleInter().isEmpty() && MyProHelperClass.getlive_status().equals("1")) {
                if (google_InterstitialAd_1 == null) {
                    GoogleInterPreload1();
                }
            }
            if (MyProHelperClass.getGoogleInter1() != null && !MyProHelperClass.getGoogleInter1().isEmpty() && MyProHelperClass.getlive_status().equals("1")) {
                if (google_InterstitialAd_2 == null) {
                    GoogleInterPreload2();
                }
            }
            if (MyProHelperClass.getGoogleInter2() != null && !MyProHelperClass.getGoogleInter2().isEmpty() && MyProHelperClass.getlive_status().equals("1")) {
                if (google_InterstitialAd_3 == null) {
                    GoogleInterPreload3();

                }
            }
        }

        /*Facebook*/
        if (MyProHelperClass.getFacebookInter() != null && !MyProHelperClass.getFacebookInter().isEmpty() && MyProHelperClass.getlive_status().equals("1")) {
            if (facebook_interstitialAd == null) {
                FacebookInterPreLoad();
            }
        }

        /*App Loving*/
        if (MyProHelperClass.getAppLovinInter() != null && !MyProHelperClass.getAppLovinInter().isEmpty() && MyProHelperClass.getlive_status().equals("1")) {
            if (applovin_interstitialAd == null) {
                AppLovingInterPreLoad();
            }
        }

        /*Unity*/
        if (MyProHelperClass.getUnityInterID() != null && !MyProHelperClass.getUnityInterID().isEmpty() && MyProHelperClass.getlive_status().equals("1")) {
            if (!UnityAdLoadChecker) {
                UnityInterPreLoad();
            }
        }
    }

}
