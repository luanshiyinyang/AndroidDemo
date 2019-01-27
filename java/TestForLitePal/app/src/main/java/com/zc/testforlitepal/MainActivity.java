package com.zc.testforlitepal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zc.testforlitepal.bean.Teacher;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplication();
        text = findViewById(R.id.query_result);
        findViewById(R.id.create_database).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "ZCNB", Toast.LENGTH_SHORT);
                try {
                    LitePal.initialize(mContext);
                    Toast.makeText(mContext, "数据库创建完成", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(mContext, "出现操作故障", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.insert_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Teacher mTeacher = new Teacher();
                mTeacher.setId(1);
                mTeacher.setAge(12);
                mTeacher.setName("李二");
                mTeacher.setValue(2);
                mTeacher.save();
                Toast.makeText(mContext, "插入成功", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.update_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Teacher mTeacher = new Teacher();
                mTeacher.setAge(18);
                mTeacher.updateAll("id = ?", "1");
                Toast.makeText(mContext, "更新成功", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.delete_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(Teacher.class, "id >= ?","1");
                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.query_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Teacher> allTeacher = LitePal.findAll(Teacher.class);
                for(Teacher mt: allTeacher){
                    text.setText(mt.getId()+" "+mt.getName()+" "+mt.getAge()+" "+mt.getValue()+"\n");
                }
                Toast.makeText(mContext, "查询成功", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
