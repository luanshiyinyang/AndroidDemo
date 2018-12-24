package com.zc.test;

/**
 * Created by 16957 on 2018/11/18.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //上下文
    private Context mContext;
    //搜索框控件，可以自定义style
    private SearchBox sbSearch;
    //底部结果统计控件
    private TextView tvBottom;
    //数据列表
    private List<String> listSearch;
    //结果列表
    private List<String> listResult;

    //历史列表控件
    private ListView mHistory ;
    //搜索结果列表控件
    private ListView mSearchResult ;
    //底部结果控件
    private TextView bottom;
    //两个继承BaseAdapter的适配器,搜索历史sf本地保存
    private SearchAdapter mResultAdapter ;
    private HistoryAdapter mHistoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        bindViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        init();
        initHistory();
        updateBottom();
    }

    private void initData() {
        //本地可供检索数据获取,每次resume就要重新渲染
        listSearch = new ArrayList<>();
        listSearch.add("123");
        listSearch.add("1234");
        listSearch.add("12345");
    }

    private void bindViews() {
        mSearchResult = (ListView) findViewById(R.id.lv_content);
        sbSearch = (SearchBox) findViewById(R.id.searchbox);
        tvBottom = (TextView) findViewById(R.id.tv_bottom);
        mHistory = (ListView) findViewById(R.id.lv_history);
        bottom = (TextView) findViewById(R.id.tv_bottom);
    }

    public void init(){
        //结果的初始化
        listResult = new ArrayList<>();
        mResultAdapter = new SearchAdapter(MainActivity.this, listResult);
        mSearchResult.setAdapter(mResultAdapter);
        mSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        //搜索框的初始化
        sbSearch.enableVoiceRecognition(this);
        sbSearch.setMenuListener(new SearchBox.MenuListener(){
            @Override
            public void onMenuClick() {
                finish();
            }
        });
        sbSearch.setSearchListener(new SearchBox.SearchListener(){

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
            }
            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }
            @Override
            public void onSearchTermChanged(String term) {
                //搜索框内容修改就进行搜索，但只要点击搜索按钮才会加入搜索历史
                search(term);
                updateBottom();
                if(listResult.size()==0){
                    mHistory.setVisibility(View.VISIBLE);
                }else {
                    mHistory.setVisibility(View.GONE);
                }
            }
            @Override
            public void onSearch(String searchTerm) {
                search(searchTerm);
                saveHistory(searchTerm);
                initHistory();
                updateBottom();
            }
            @Override
            public void onResultClick(SearchResult result) {
                //React to a result being clicked
            }
            @Override
            public void onSearchCleared() {
                //Called when the clear button is clicked
            }

        });
    }

    private void initHistory(){
        final List<String> history = getHistory();
        mHistoryAdapter = new HistoryAdapter(MainActivity.this,history);
        mHistory.setAdapter(mHistoryAdapter);
        mHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sbSearch.populateEditText(history.get(position));
                sbSearch.setSearchString(history.get(position));
            }
        });

    }

    private void search(String newText){
        //若搜索内容为空
        if(newText.isEmpty()){
            listResult.clear();
        }
        else{
            for (String node : listSearch) {
                //在数据集遍历搜索
                if (node.contains(newText)) {
                    if(listResult.indexOf(node)==-1) {
                        //如果结果集不含有它（为了防止结果重复）
                        listResult.add(node);
                    }
                }else{
                    //搜索内容搜索不到相关 检测是否之前有加入结果集 有则删除
                    if(listResult.indexOf(node)!=-1) {
                        listResult.remove(node);
                    }
                }
            }
        }
        mResultAdapter.notifyDataSetChanged();
    }



    private ArrayList<String> getHistory() {
        SharedPreferences reader = getSharedPreferences("history", MODE_PRIVATE);
        String data = reader.getString("data_history", "");
        ArrayList<String> history = new ArrayList<>();
        String [] get=  data.split("\\|");
        for( String str:get){
            if(! history.contains(str) && !str.isEmpty()){
                history.add(str);
            }
        }
        return history;
    }

    private void saveHistory(String s){
        StringBuilder sb = new StringBuilder();
        SharedPreferences.Editor editor = getSharedPreferences("history",MODE_PRIVATE).edit();
        for (String str: getHistory()){
            sb.append(str);
            sb.append("|");
        }
        sb.append(s);
        editor.putString("data_history",sb.toString());
        editor.apply();
    }


    private void updateBottom(){
        if(sbSearch.getSearchText().trim().isEmpty()){
            bottom.setVisibility(View.GONE);
            return;
        }
        tvBottom.setVisibility(View.VISIBLE);
        tvBottom.setText("找到了 "+ listResult.size() +" 条记录");

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
