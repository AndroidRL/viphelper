package com.appwood.mylibrarys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import java.util.ArrayList;
import java.util.List;

public class BigAnimation {

    public static int auto_notShow_ads_native = 0;
    private static com.google.android.gms.ads.nativead.NativeAd GoogleNativeBig = null;
    public static int mix_ads_native = 0;
    public static MaxNativeAdLoader nativeAdLoader;
    public static MaxAd nativeAd;
    public static Activity activity;
    public static ViewGroup viewGroup;
    public static LinearLayout linearLayout;
    public static RelativeLayout addcontain;
    public static RelativeLayout ad_native_fb;
    public static FrameLayout app_loving_native;
    public static LinearLayout custom_native;

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

    /* Native Main Code*/
    public static void Top_animation(Activity activity1, ViewGroup viewGroup1, LinearLayout linearLayout1, RelativeLayout addcontain1, RelativeLayout ad_native_fb1, FrameLayout app_loving_native1, LinearLayout custom_native1) {
        activity = activity1;
        viewGroup = viewGroup1;
        linearLayout = linearLayout1;
        addcontain = addcontain1;
        ad_native_fb = ad_native_fb1;
        app_loving_native = app_loving_native1;
        custom_native = custom_native1;


        if (checkConnection(activity)) {
            /**
             * Skip Ads
             */
            if (MyHelpers.getCounter_Native() == 0) {
                return;
            }
            if (MyHelpers.getCounter_Native() != 5000) {
                auto_notShow_ads_native++;
                if (MyHelpers.getCounter_Native() + 1 == auto_notShow_ads_native) {
                    auto_notShow_ads_native = 0;
                    return;
                }
            }
            /**
             * Mix Ads
             */
            if (MyHelpers.getmix_ad_on_off().equals("1")) {
                if (MyHelpers.getmix_ad_native().equals("0")) {
                    RegularAds();
                } else {
                    if (MyHelpers.getmix_ad_native().length() == 2) {
                        Mix2Ads(MyHelpers.getmix_ad_native());  // 2 ads
                    } else if (MyHelpers.getmix_ad_native().length() == 3) {
                        Mix3Ads(MyHelpers.getmix_ad_native()); // 3 ads
                    } else if (MyHelpers.getmix_ad_native().length() == 4) {
                        Mix4Ads(MyHelpers.getmix_ad_native()); // 4 ads
                    }
                }
                return;
            }
            /**
             * Regular Ads
             */

            RegularAds();

        }
    }

    private static void RegularAds() {
        if (MyHelpers.getGoogleEnable().equals("1")) {
            NativeAd_1();
        } else if (MyHelpers.getFacebookEnable().equals("1")) {
            FacebookNative();
        } else if (MyHelpers.getAppLovinEnable().equals("1")) {
            APPLovinNative();
        } else if (MyHelpers.getCustomEnable().equals("1")) {
            CustomAdsShow();
        }
    }

    /**
     * Google Native
     */
    public static void NativeAd_1() {
        ad_native_fb.setVisibility(View.GONE);
        if (GoogleNativeBig == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig = null;
                    NativeAd_2();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig = null;
                    NativeAd_1();
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView2 = (com.google.android.gms.ads.nativead.NativeAdView) nativeAdView.findViewById(R.id.ad_view);
        populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
    }

    public static void NativeAd_2() {
        if (GoogleNativeBig == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative1());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig = null;
                    NativeAd_3();

                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig = null;
                    NativeAd_2();
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView2 = (com.google.android.gms.ads.nativead.NativeAdView) nativeAdView.findViewById(R.id.ad_view);
        populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
    }

    public static void NativeAd_3() {
        if (GoogleNativeBig == null) {
            AdLoader.Builder builder2 = new AdLoader.Builder(activity, MyHelpers.getGoogleNative2());
            builder2.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                    com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
                    GoogleNativeBig = nativeAd;
                    populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
                }
            });
            builder2.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder2.withAdListener(new AdListener() {
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    GoogleNativeBig = null;

                    CustomAdsShow();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    GoogleNativeBig = null;
                    NativeAd_3();
                }
            }).build().loadAd(new AdRequest.Builder().build());
            return;
        }
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView = (com.google.android.gms.ads.nativead.NativeAdView) activity.getLayoutInflater().inflate(R.layout.ad_google_big_native, (ViewGroup) null);
        com.google.android.gms.ads.nativead.NativeAdView nativeAdView2 = (com.google.android.gms.ads.nativead.NativeAdView) nativeAdView.findViewById(R.id.ad_view);
        populateUnifiedNativeAdView(GoogleNativeBig, nativeAdView, activity, viewGroup, addcontain);
    }

    public static void populateUnifiedNativeAdView(com.google.android.gms.ads.nativead.NativeAd nativeAd, com.google.android.gms.ads.nativead.NativeAdView nativeAdView, Activity activity, ViewGroup viewGroup, RelativeLayout addcontain) {

        addcontain.setVisibility(View.VISIBLE);
        ad_native_fb.setVisibility(View.GONE);
        app_loving_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.GONE);

        nativeAdView.setMediaView((com.google.android.gms.ads.nativead.MediaView) nativeAdView.findViewById(R.id.ad_media));
        ((com.google.android.gms.ads.nativead.MediaView) nativeAdView.findViewById(R.id.ad_media)).setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.ad_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.ad_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.ad_call_to_action));
        nativeAdView.setIconView(nativeAdView.findViewById(R.id.ad_app_icon));
        nativeAdView.getMediaView().setMediaContent(GoogleNativeBig.getMediaContent());
        nativeAdView.findViewById(R.id.ad_call_to_action).setBackground(ContextCompat.getDrawable(activity, R.drawable.app_btn));
        addcontain.setVisibility(View.VISIBLE);
        try {
            ((TextView) nativeAdView.getHeadlineView()).setText(GoogleNativeBig.getHeadline());
            if (GoogleNativeBig.getBody() == null) {
                nativeAdView.getBodyView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getBodyView().setVisibility(View.VISIBLE);
                ((TextView) nativeAdView.getBodyView()).setText(GoogleNativeBig.getBody());
            }
            if (GoogleNativeBig.getCallToAction() == null) {
                nativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
            } else {
                nativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
                if (MyHelpers.getGooglebutton_name() != null && !MyHelpers.getGooglebutton_name().isEmpty()) {
                    ((Button) nativeAdView.getCallToActionView()).setText(MyHelpers.getGooglebutton_name());
                } else {
                    ((Button) nativeAdView.getCallToActionView()).setText(GoogleNativeBig.getCallToAction());
                }
                ((Button) nativeAdView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(MyHelpers.getGooglebutton_color())));
            }
            if (GoogleNativeBig.getIcon() == null) {
                nativeAdView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) nativeAdView.getIconView()).setImageDrawable(GoogleNativeBig.getIcon().getDrawable());
                nativeAdView.getIconView().setVisibility(View.VISIBLE);
            }
            nativeAdView.setNativeAd(GoogleNativeBig);
            VideoController videoController = nativeAd.getMediaContent().getVideoController();
            if (videoController.hasVideoContent()) {
                videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            }
        } catch (Exception unused) {
        }
        viewGroup.removeAllViews();
        viewGroup.addView(nativeAdView);
    }

    /**
     * Facebook Native
     */
    private static void FacebookNative() {

        ad_native_fb.setVisibility(View.VISIBLE);
        addcontain.setVisibility(View.GONE);
        app_loving_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.GONE);
        com.facebook.ads.NativeAd nativeAd = new com.facebook.ads.NativeAd(activity, MyHelpers.getFacebookNative());
        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                com.facebook.ads.NativeAd nativeAd_2 = new com.facebook.ads.NativeAd(activity, MyHelpers.getFacebookNative1());
                NativeAdListener nativeAdListener_2 = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {

                    }

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {

                        com.facebook.ads.NativeAd nativeAd_3 = new com.facebook.ads.NativeAd(activity, MyHelpers.getFacebookNative2());
                        NativeAdListener nativeAdListener_3 = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {

                            }

                            @Override
                            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                                CustomAdsShow();

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                if (nativeAd_3 == null || nativeAd_3 != ad) {
                                    return;
                                }

                                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, ad_native_fb, false);
                                fbPopulateNativeAdView(nativeAd_3, adView);
                                ad_native_fb.removeAllViews();
                                ad_native_fb.addView(adView);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        };
                        nativeAd_3.loadAd(nativeAd_3.buildLoadAdConfig().withAdListener(nativeAdListener_3).build());

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        if (nativeAd_2 == null || nativeAd_2 != ad) {
                            return;
                        }

                        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, ad_native_fb, false);
                        fbPopulateNativeAdView(nativeAd_2, adView);
                        ad_native_fb.removeAllViews();
                        ad_native_fb.addView(adView);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                };
                nativeAd_2.loadAd(nativeAd_2.buildLoadAdConfig().withAdListener(nativeAdListener_2).build());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }

                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ad_fb_native_layout, ad_native_fb, false);
                fbPopulateNativeAdView(nativeAd, adView);
                ad_native_fb.removeAllViews();
                ad_native_fb.addView(adView);

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(nativeAdListener).build());

    }

    public static void fbPopulateNativeAdView(com.facebook.ads.NativeAd nativeAd, LinearLayout adView) {

        nativeAd.unregisterView();

        // Create native UI using the ad metadata.
        com.facebook.ads.MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    /**
     * App Loving
     */
    private static void APPLovinNative() {
        addcontain.setVisibility(View.GONE);
        ad_native_fb.setVisibility(View.GONE);
        app_loving_native.setVisibility(View.VISIBLE);
        custom_native.setVisibility(View.GONE);
        nativeAdLoader = new MaxNativeAdLoader(MyHelpers.getAppLovinNative(), activity);
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                if (nativeAd != null) {
                    nativeAdLoader.destroy(nativeAd);
                }
                nativeAd = ad;
                app_loving_native.removeAllViews();
                app_loving_native.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                //Fails
                CustomAdsShow();
            }


            @Override
            public void onNativeAdClicked(final MaxAd ad) {
            }
        });
        nativeAdLoader.loadAd();
    }

    /**
     * Custom Ads
     */
    public static void CustomAdsShow() {
        addcontain.setVisibility(View.GONE);
        ad_native_fb.setVisibility(View.GONE);
        app_loving_native.setVisibility(View.GONE);
        custom_native.setVisibility(View.VISIBLE);

        int ads_number = MyHelpers.getRandomNumber(0, Splash.adsModals.size() - 1);
        LinearLayout native_view = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.custom_native, (ViewGroup) null);
        AppCompatButton btn_install = native_view.findViewById(R.id.btn_install);
        RelativeLayout full_click = native_view.findViewById(R.id.full_click);
        TextView app_name = native_view.findViewById(R.id.app_name);
        TextView app_shot = native_view.findViewById(R.id.app_shot);
        ImageView app_icon = native_view.findViewById(R.id.app_icon);
        ImageView ads_banner = native_view.findViewById(R.id.ads_banner);
        Glide.with(activity).load(Splash.adsModals.get(ads_number).getApp_logo()).into(app_icon);
        Glide.with(activity).load(Splash.adsModals.get(ads_number).getApp_banner()).into(ads_banner);
        app_name.setText(Splash.adsModals.get(ads_number).getAd_app_name());
        app_shot.setText(Splash.adsModals.get(ads_number).getApp_description());
        btn_install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        full_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + Splash.adsModals.get(ads_number).getApp_name())));
                }
            }
        });
        custom_native.removeAllViews();
        custom_native.addView(native_view);

    }

    /**
     * Mix Ads
     */
    private static void Mix2Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        if (MyHelpers.getmix_ad_counter() != 5000) {
            mix_ads_native++;
            if (MyHelpers.getmix_ad_counter() + 1 == mix_ads_native) {
                MixAdsShowNative(second_ads);
                mix_ads_native = 0;
            } else {
                MixAdsShowNative(first_ads);
            }
        } else {
            if (mix_ads_native == 0) {
                mix_ads_native = 1;
                MixAdsShowNative(first_ads);
            } else if (mix_ads_native == 1) {
                mix_ads_native = 0;
                MixAdsShowNative(second_ads);
            }
        }
    }

    private static void Mix3Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        if (mix_ads_native == 0) {
            mix_ads_native = 1;
            MixAdsShowNative(first_ads);
        } else if (mix_ads_native == 1) {
            mix_ads_native = 2;
            MixAdsShowNative(second_ads);
        } else if (mix_ads_native == 2) {
            mix_ads_native = 0;
            MixAdsShowNative(three_ads);
        }
    }

    private static void Mix4Ads(String s) {
        char first_ads = s.charAt(0);
        char second_ads = s.charAt(1);
        char three_ads = s.charAt(2);
        char four_ads = s.charAt(3);
        if (mix_ads_native == 0) {
            mix_ads_native = 1;
            MixAdsShowNative(first_ads);
        } else if (mix_ads_native == 1) {
            mix_ads_native = 2;
            MixAdsShowNative(second_ads);
        } else if (mix_ads_native == 2) {
            mix_ads_native = 3;
            MixAdsShowNative(three_ads);
        } else if (mix_ads_native == 3) {
            mix_ads_native = 0;
            MixAdsShowNative(four_ads);
        }
    }

    private static void MixAdsShowNative(char ads) {
        String value = String.valueOf(ads);
        if (value.equals("g")) {
            NativeAd_1();
        } else if (value.equals("f")) {
            FacebookNative();
        } else if (value.equals("a")) {
            APPLovinNative();
        } else if (value.equals("c")) {
            CustomAdsShow();
        }
    }

}
