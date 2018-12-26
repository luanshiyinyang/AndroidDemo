package com.zc.testforrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<String> mData;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View myView;
        TextView content;
        public ViewHolder(View view){
            super(view);
            myView = view;
            content = view.findViewById(R.id.content);
        }
    }

    public MyRecyclerViewAdapter(List<String> Data){
        mData = Data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content, null, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                String str = mData.get(position);
                Toast.makeText(view.getContext(), "你点击了第"+Integer.toString(position)+"项", Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = mData.get(position);
        holder.content.setText(name);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
