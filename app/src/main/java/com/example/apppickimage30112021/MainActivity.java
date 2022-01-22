package com.example.apppickimage30112021;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity{

    ProgressBar mProgressBarTime;
    TextView mTvPoint;
    ImageView mImgRandom, mImgPick;
    String[] mArrNameImages;
    int mResourceIdRandom;
    Random mRandom;
    long mTotalTime;
    int mPoint = 0;
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
        MyCountDownTimer.getInstance().onListenerTime(new MyCountDownTimer.OnListenerMyCountDown() {
            @Override
            public void onTick(long currentTime) {
                Log.d("BBB",currentTime + "");
                mProgressBarTime.setProgress((int) (currentTime / 1000));
            }

            @Override
            public void onFinish() {
                mProgressBarTime.setProgress(0);
            }
        });
        // handle
        randomImage();

        mImgPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                activityResultLauncher.launch(intent);
            }
        });

    }

    private void init() {
        mProgressBarTime = findViewById(R.id.progressBarTime);
        mTvPoint = findViewById(R.id.textViewPoint);
        mImgPick = findViewById(R.id.imgPick);
        mImgRandom = findViewById(R.id.imgRandom);

        mRandom = new Random();
        mTotalTime = 6000;

        // set point
        mTvPoint.setText(mPoint + "");

        //set max progressbar
        mProgressBarTime.setMax((int) (mTotalTime / 1000));
        mProgressBarTime.setProgress((int) (mTotalTime / 1000));
    }

    private void randomImage() {
        mArrNameImages = getResources().getStringArray(R.array.arr_image);
        int index = mRandom.nextInt(mArrNameImages.length);
        mResourceIdRandom = getResources().getIdentifier(mArrNameImages[index], "drawable", getPackageName());
        mImgRandom.setImageResource(mResourceIdRandom);
        MyCountDownTimer.getInstance().countDown(mTotalTime, 1000);
    }



    // Get data from activity
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        int resourcePick = data.getIntExtra("resourceId",-1);
                        mImgPick.setImageResource(resourcePick);

                        if (resourcePick == mResourceIdRandom){
                            mPoint += 1;
                            mTvPoint.setText(mPoint + "");
                            Toast.makeText(MainActivity.this, "Chuẩn bị cho hình tiếp theo", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    randomImage();
                                }
                            },1500);
                        }else{
                            Toast.makeText(MainActivity.this, "Bạn đã thua với số điểm : " + mPoint , Toast.LENGTH_SHORT).show();
                            mProgressBarTime.setProgress(0);
                        }
                    }
                    if (result.getResultCode() == Activity.RESULT_CANCELED){
                        Intent data = result.getData();
                        if (data != null ){
                            String message = data.getStringExtra("message");
                            if (!message.isEmpty()){
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                                mProgressBarTime.setProgress(0);
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Bạn không chọn hình", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Bạn đã thua với số điểm : " + mPoint , Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


}