package com.example.apppickimage30112021;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ProgressBar mProgressBarTime;
    TextView mTvPoint;
    ImageView mImgRandom,mImgPick;
    String[] mArrNameImages;
    int mResourceIdRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBarTime = findViewById(R.id.progressBarTime);
        mTvPoint = findViewById(R.id.textViewPoint);
        mImgPick = findViewById(R.id.imgPick);
        mImgRandom = findViewById(R.id.imgRandom);

        // random 1 tấm bất kỳ trong 18 tấm
        mArrNameImages = getResources().getStringArray(R.array.arr_image);
        mResourceIdRandom = getResources().getIdentifier(mArrNameImages[2],"drawable",getPackageName());
        mImgRandom.setImageResource(mResourceIdRandom);
    }


}