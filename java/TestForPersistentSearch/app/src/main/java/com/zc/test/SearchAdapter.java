package com.zc.test;

/**
 * Created by 16957 on 2018/11/18.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;


public class SearchAdapter extends ArrayAdapter<String> {

    private Context mContext;
    public SearchAdapter(Context context, List<String> data){
        super (context, R.layout.item_search,data);
        this.mContext=context;
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent) {
        String node = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_search,parent,false);
        TextView title = (TextView) view.findViewById(R.id.tv_search_result);
        if(title!=null)title.setText(node);
        return view;
    }
}




