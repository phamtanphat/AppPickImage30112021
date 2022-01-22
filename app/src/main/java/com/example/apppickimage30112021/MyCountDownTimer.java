package com.example.apppickimage30112021;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MyCountDownTimer {
    public static MyCountDownTimer instance;
    private CountDownTimer countDownTimer ;
    OnListenerMyCountDown onListenerMyCountDown;

    private MyCountDownTimer(AppCompatActivity activity){
        onListenerMyCountDown = (OnListenerMyCountDown) activity;
    }

    public static MyCountDownTimer getInstance(AppCompatActivity activity){
        if (instance == null){
            instance = new MyCountDownTimer(activity);
        }
        return instance;
    }

    public void countDown(long totalTime , long interval){
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(totalTime,interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished >= 1000){
                    Log.d("BBB", " onListener "  + onListenerMyCountDown);
                    if (onListenerMyCountDown != null){
                        onListenerMyCountDown.onTick(millisUntilFinished);
                    }
                }
            }

            @Override
            public void onFinish() {
                if (onListenerMyCountDown != null){
                    onListenerMyCountDown.onFinish();
                }
            }
        }.start();
    }

    public void cancelTime(){
        if (countDownTimer != null){
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    interface OnListenerMyCountDown{
        void onTick(long currentTime);
        void onFinish();
    }
}
