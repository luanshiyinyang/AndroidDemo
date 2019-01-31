package com.zc.testforgreendao;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zc.greendao.DaoMaster;
import com.zc.greendao.DaoSession;
import com.zc.testforgreendao.bean.Teacher;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DaoSession mdaoSession;
    private Context mContext;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        text = findViewById(R.id.text);
        findViewById(R.id.init).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupDataBase(mContext);
            }
        });
        findViewById(R.id.insert_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
        findViewById(R.id.delete_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
        findViewById(R.id.query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query();
            }
        });
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }




    private void setupDataBase(Context context){
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, "my-db", null);
        Database db = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        mdaoSession = daoMaster.newSession();
        Toast.makeText(context, "初始化完成", Toast.LENGTH_SHORT).show();
    }

    private void insert() {
        Teacher teacher = new Teacher(null, "王老师", 23);
        try {
            mdaoSession.getTeacherDao().insert(teacher);
        }catch (Exception e){
            Toast.makeText(mContext, "插入失败", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(mContext, "插入成功", Toast.LENGTH_SHORT).show();
    }
    private void delete() {
        try {
            mdaoSession.getTeacherDao().deleteByKey(1l);
        }catch (Exception e){
            Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
    }
    private void query() {
        List<Teacher> mList = mdaoSession.getTeacherDao().loadAll();
        String rst = new String();
        for(Teacher mt : mList){
            rst += mt.getId()+" "+mt.getName()+" "+mt.getAge()+" "+"\n";
        }
        text.setText(rst);
        Toast.makeText(mContext, "查询成功", Toast.LENGTH_SHORT).show();
    }
    private void update(){
        try {
            Teacher mt = mdaoSession.load(Teacher.class, 1l);
            mt.setName("修改后的名字");
            mdaoSession.getTeacherDao().update(mt);
            Toast.makeText(mContext, "修改成功",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(mContext, "想要修改的对象不存在",Toast.LENGTH_SHORT).show();
        }

    }

}
