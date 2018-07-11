package com.katsuro.alexey.minecafe;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import java.util.Locale;

/**
 * Created by alexey on 7/11/18.
 */

public class AdBlocker {
    private static final String TAG = AdBlocker.class.getSimpleName();
    private static AdBlocker sAdBlocker;
    private Context mContext;

    boolean interstitialAdAvailable = true;
    boolean rewardedAdAvailable = true;
    private CountDownTimer mTimerRewarded;
    private CountDownTimer mTimerInterstitial;

    private AdBlocker(Context context) {
       // mContext = context.getApplicationContext();
    }

    public static AdBlocker get(Context context){
        if(sAdBlocker==null){
            sAdBlocker = new AdBlocker(context);
        }
        return sAdBlocker;
    }

    public void blockInterstitialAdOnTime(long time) {
        interstitialAdAvailable = false;
        mTimerInterstitial = new CountDownTimer(time,time/10) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG,String.format(Locale.getDefault(),
                        "Interstitial Ad will be available after %d seconds",millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                Log.d(TAG, String.format(Locale.getDefault(),
                        "Interstitial Ad is available!!!"));
                interstitialAdAvailable = true;
            }
        };
        mTimerInterstitial.start();
    }

    public void blockRewardedAdOnTime(long time) {
        rewardedAdAvailable = false;
        mTimerRewarded = new CountDownTimer(time,time/10) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(TAG,String.format(Locale.getDefault(),
                        "Rewarded Ad will be available after %d seconds",millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                Log.d(TAG,String.format(Locale.getDefault(),
                        "Rewarded Ad is available!!!"));
                rewardedAdAvailable = true;
            }
        };
        mTimerRewarded.start();

    }

    public boolean isInterstitialAdAvailable() {
        Log.d(TAG,String.format(Locale.getDefault(),
                "Is interstitial Ad available: %b",interstitialAdAvailable));
        return interstitialAdAvailable;
    }

    public boolean isRewardedAdAvailable() {
        Log.d(TAG,String.format(Locale.getDefault(),
                "Is rewarded Ad available: %b",interstitialAdAvailable));
        return rewardedAdAvailable;
    }

    public void cancelBlocks(){
        Log.d(TAG,"Blocks was canceled");
        if(mTimerRewarded !=null) {
            mTimerRewarded.cancel();
        }
        if(mTimerInterstitial!=null) {
            mTimerInterstitial.cancel();
        }
    }
}
