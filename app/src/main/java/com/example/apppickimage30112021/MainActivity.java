package com.example.apppickimage30112021;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ProgressBar mProgressBarTime;
    TextView mTvPoint;
    ImageView mImgRandom, mImgPick;
    String[] mArrNameImages;
    int mResourceIdRandom;
    Random mRandom;
    MyCountDownTimer mMyCountDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // random 1 tấm bất kỳ trong 18 tấm
        init();
        randomImage();
        mMyCountDown.getInstance().countDown(5000, 1000, new MyCountDownTimer.OnListenerMyCountDown() {
            @Override
            public void onTick(long currentTime) {
                Log.d("BBB",currentTime + "");
            }

            @Override
            public void onFinish() {
                Log.d("BBB","onFinish");
            }
        });
    }

    private void init(){
        mProgressBarTime = findViewById(R.id.progressBarTime);
        mTvPoint = findViewById(R.id.textViewPoint);
        mImgPick = findViewById(R.id.imgPick);
        mImgRandom = findViewById(R.id.imgRandom);

        mRandom = new Random();
    }

    private void randomImage() {
        mArrNameImages = getResources().getStringArray(R.array.arr_image);
        int index = mRandom.nextInt(mArrNameImages.length);
        mResourceIdRandom = getResources().getIdentifier(mArrNameImages[index], "drawable", getPackageName());
        mImgRandom.setImageResource(mResourceIdRandom);
    }


}