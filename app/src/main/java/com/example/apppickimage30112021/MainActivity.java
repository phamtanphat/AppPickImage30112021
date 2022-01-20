package com.example.apppickimage30112021;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Handler mHandler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CountDownTimer countDown = new CountDownTimer(10000,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                if (millisUntilFinished > 1000){
//
//                }
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };
//        countDown.start();
        mHandler = new Handler();
        runnable = periodic(4);

    }
    public Runnable periodic(int count){
        runnable = new Runnable() {
            @Override
            public void run() {
                if (count - 1 >= 0){
                    int newCount = count - 1;
                    periodic(newCount);
                    Log.d("BBB","Hello");
                }
            }
        };
        mHandler.postDelayed(runnable,1000);
        return runnable;
    }


}