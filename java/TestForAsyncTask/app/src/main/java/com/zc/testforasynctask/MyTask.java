package com.zc.testforasynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyTask extends AsyncTask<String, String, Item> {
    private Context mContext;
    private TextView tv;

    public MyTask(Context mContext, TextView tv) {
        this.mContext = mContext;
        this.tv = tv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(mContext, "开始寻找网络资源", Toast.LENGTH_SHORT).show();
    }

    private String baseUrl = "http://13.250.1.159:8000/api/";
    @Override
    protected Item doInBackground(String... strings) {
        Item result;
        try {
            Thread.sleep(0b1111101000);
            OkHttpClient client = new OkHttpClient();
            String nowUrl = baseUrl + "merchants/" + strings[0] + ".json";
            Request request = new Request.Builder().url(nowUrl).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                result = parseItemJsonObject(response.body().string());
                Log.i("msg", result.getName());
            }
            else {
                Toast.makeText(mContext, "无结果", Toast.LENGTH_SHORT).show();
                return null;
            }
        }catch (Exception e){
            Toast.makeText(mContext, "网络连接异常", Toast.LENGTH_SHORT).show();
            return null;
        }
        return result;

    }

    @Override
    protected void onPostExecute(Item item) {
        super.onPostExecute(item);
        if(item != null){
            tv.setText(item.getName());
        }
        else {
            tv.setText("数据解析为空");
        }

    }

    private  Item parseItemJsonObject(String jsonData) {
        // 解析Json对象
        try {
            if (jsonData != null) {
                //创建一个Gson对象
                Gson gson = new Gson();
                Item item = gson.fromJson(jsonData,Item.class);
                return item;
            }
            else {
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
