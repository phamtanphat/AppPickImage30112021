package com.example.apppickimage30112021;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ProgressBar mProgressBarTime;
    TextView mTvPoint;
    ImageView mImgRandom, mImgPick;
    String[] mArrNameImages;
    int mResourceIdRandom;
    Random mRandom;
    long mTotalTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // random 1 tấm bất kỳ trong 18 tấm
        init();
        event();

    }

    private void event() {
        // listener
        MyCountDownTimer.getInstance().countDown(mTotalTime, 1000, new MyCountDownTimer.OnListenerMyCountDown() {
            @Override
            public void onTick(long currentTime) {
                mProgressBarTime.setProgress((int) (currentTime / 1000));
                Log.d("BBB","Current " + currentTime);
            }

            @Override
            public void onFinish() {
                mProgressBarTime.setProgress(0);
                Log.d("BBB","finish ");
            }
        });
        // handle
        randomImage();

        mImgPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                activityResultLauncher.launch(intent);
            }
        });

    }

    private void init(){
        mProgressBarTime = findViewById(R.id.progressBarTime);
        mTvPoint = findViewById(R.id.textViewPoint);
        mImgPick = findViewById(R.id.imgPick);
        mImgRandom = findViewById(R.id.imgRandom);

        mRandom = new Random();
        mTotalTime = 5000;

        //set max progressbar
        mProgressBarTime.setMax((int) (mTotalTime / 1000));
        mProgressBarTime.setProgress((int) (mTotalTime / 1000));
    }

    private void randomImage() {
        mArrNameImages = getResources().getStringArray(R.array.arr_image);
        int index = mRandom.nextInt(mArrNameImages.length);
        mResourceIdRandom = getResources().getIdentifier(mArrNameImages[index], "drawable", getPackageName());
        mImgRandom.setImageResource(mResourceIdRandom);
    }


    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        // There are no request codes
//                        Intent data = result.getData();
//                        doSomeOperations();
//                    }
                }
            });

}