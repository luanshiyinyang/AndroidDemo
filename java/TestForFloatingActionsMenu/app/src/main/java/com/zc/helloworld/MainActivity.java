package com.zc.helloworld;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton b1;
    FloatingActionButton b2;
    FloatingActionButton b3;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
       // 绑定组件
        bindViews();
        //设置事件监听
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "你点击了b1按钮", Toast.LENGTH_SHORT).show();
            }});
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "你点击了b2按钮", Toast.LENGTH_SHORT).show();
            }});
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "你点击了b3按钮", Toast.LENGTH_SHORT).show();
            }});
    }

    private void bindViews() {
        b1 = (FloatingActionButton) findViewById(R.id.fab_b1);
        b2 = (FloatingActionButton) findViewById(R.id.fab_b2);
        b3 = (FloatingActionButton) findViewById(R.id.fab_b3);
    }


}