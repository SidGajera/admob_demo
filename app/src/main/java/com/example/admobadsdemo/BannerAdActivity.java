package com.example.admobadsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;

public class BannerAdActivity extends AppCompatActivity {

    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/9214589741";
    private FrameLayout adaptiveBannerAd;
    private AdView adView;

    private AdView bannerAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_ad);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        MobileAds.setRequestConfiguration(
                new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345")).build());

        adaptiveBannerAd = findViewById(R.id.adaptiveBannerAd);
        bannerAdView = findViewById(R.id.bannerAd);

        AdRequest adRequest = new AdRequest.Builder().build();
        bannerAdView.loadAd(adRequest);
        Log.e("TAG", "ads: " + bannerAdView);

        adaptiveBannerAd.post(new Runnable() {
            @Override
            public void run() {
                loadBanner();
            }
        });
    }

    @Override
    public void onPause() {
        if (adView != null && bannerAdView != null) {
            adView.pause();
            bannerAdView.pause();
        }
        super.onPause();
    }

    /*
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null && bannerAdView != null) {
            adView.resume();
            bannerAdView.resume();
        }
    }

    /*
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (adView != null && bannerAdView != null) {
            adView.destroy();
            bannerAdView.destroy();
        }
        super.onDestroy();
    }

    private void loadBanner() {
        // Create an ad request.
        adView = new AdView(this);
        adView.setAdUnitId(AD_UNIT_ID);
        adaptiveBannerAd.removeAllViews();
        adaptiveBannerAd.addView(adView);

        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        // Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = adaptiveBannerAd.getWidth();

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }
}