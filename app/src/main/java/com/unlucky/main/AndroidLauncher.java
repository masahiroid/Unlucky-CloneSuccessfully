package com.unlucky.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.unlucky.main.Unlucky;

public class AndroidLauncher extends AndroidApplication {
	private static final String TAG = "AndroidLauncher";
	private RelativeLayout layout;
	private RelativeLayout.LayoutParams params;
	private AdView bannerAd;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView = initializeForView(new Unlucky(), config);

		////////// Define the layout
		layout = new RelativeLayout(this);
		layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
					   ViewGroup.LayoutParams.MATCH_PARENT);
		// タイトルバーを消す
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ステータスバーを消す
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		params = new RelativeLayout.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT,
			ViewGroup.LayoutParams.WRAP_CONTENT);

		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);

		bannerAd = new AdView(this);

		//The ID below is a test ID: you need to add your Ad-Unit ID below
		bannerAd.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
		bannerAd.setAdSize(AdSize.SMART_BANNER);

		layout.addView(bannerAd, params);
		setContentView(layout);

		AdRequest ad = new AdRequest.Builder().build();
		bannerAd.loadAd(ad);

	}
}
