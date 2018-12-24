package com.zc.testforcircleimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleImageView civ = findViewById(R.id.circle_image_view);
    }
}
