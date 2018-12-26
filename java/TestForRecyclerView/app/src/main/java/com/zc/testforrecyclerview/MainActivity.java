package com.zc.testforrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        for(int i=0;i<20;i++){
            Log.d("zc", Integer.toString(i));
            data.add(Integer.toString(i));
        }
    }
}
