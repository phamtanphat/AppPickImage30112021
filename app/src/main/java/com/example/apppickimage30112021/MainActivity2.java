package com.example.apppickimage30112021;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity2 extends AppCompatActivity {

    TableLayout mTbLayout;
    String[] mArrNameImages;
    int mPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTbLayout = findViewById(R.id.tableLayout);

        mArrNameImages = getResources().getStringArray(R.array.arr_image);
        Collections.shuffle(Arrays.asList(mArrNameImages));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        // số cột : 3
        // số dòng : 6

        for (int i = 0 ; i < 6 ; i++){
            TableRow tableRow = new TableRow(this);
            for (int y = 0 ; y < 3 ; y++){
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(width / 3,width / 3);
                int resourceImage = getResources().getIdentifier(mArrNameImages[mPosition], "drawable", getPackageName());
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(resourceImage);
                layoutParams.gravity =  Gravity.CENTER;
                imageView.setLayoutParams(layoutParams);
                tableRow.addView(imageView);
                mPosition += 1;
            }
            mTbLayout.addView(tableRow);
        }
    }
}