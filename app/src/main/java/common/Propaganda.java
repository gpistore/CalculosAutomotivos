package common;

import android.app.Activity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class Propaganda {

    private InterstitialAd mInterstitialAd;
    private boolean click = true;

    public Propaganda(Activity activity){
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId("ca-app-pub-2664724094770763/3644317232");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            //@Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("3E57B5136D19E0854E00586D5E6A581A")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    public void mostra(){
        if (mInterstitialAd.isLoaded() && click) {
            mInterstitialAd.show();
            click = false;
        }else{
            click = true;
        }

    }

}