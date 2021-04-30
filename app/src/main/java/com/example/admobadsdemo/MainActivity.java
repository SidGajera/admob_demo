package com.example.admobadsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bannerAd, interstitialAd, nativeAd, rewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerAd = findViewById(R.id.bannerAd);
        interstitialAd = findViewById(R.id.interstitialAd);
        nativeAd = findViewById(R.id.NativeAd);
        rewardedAd = findViewById(R.id.rewardedAd);

        bannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BannerAdActivity.class);
                startActivity(intent);
            }
        });

        interstitialAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InterstitialAdActivity.class);
                startActivity(intent);
            }
        });

        nativeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NativeAdActivity.class);
                startActivity(intent);
            }
        });

        rewardedAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RewardedAdActivity.class);
                startActivity(intent);
            }
        });
    }
}