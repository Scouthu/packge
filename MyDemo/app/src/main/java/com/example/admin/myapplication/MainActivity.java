package com.example.admin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<FatherContent> fatherContentList;
    private MyAdapter myAdapter;
    protected Context context;

    //得到选项的index
    private List<FatherContent> listAfterOpitionOld = new ArrayList<>();
    private ArrayList<Integer> grouplist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_father);
        context = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //给这个爸爸的recycleview赋值
        fatherContentList = new ArrayList<>();

        FatherContent fatherContent1 = new FatherContent();
        fatherContent1.setTitle("你觉得我帅吗");
        fatherContentList.add(fatherContent1);

        FatherContent fatherContent2 = new FatherContent();
        fatherContent2.setTitle("你觉得我超级帅吗吗");
        fatherContentList.add(fatherContent2);

        FatherContent fatherContent3 = new FatherContent();
        fatherContent3.setTitle("你觉得我帅吗你觉得我超级帅吗吗");
        fatherContentList.add(fatherContent3);

        FatherContent fatherContent4 = new FatherContent();
        fatherContent4.setTitle("你觉得你觉得我帅吗你觉得我超级帅吗吗我帅吗");
        fatherContentList.add(fatherContent4);

        FatherContent fatherContent5 = new FatherContent();
        fatherContent5.setTitle("你觉得你觉得我帅吗你觉得我超级帅吗吗我帅吗");
        fatherContentList.add(fatherContent5);
        fatherContentList.add(fatherContent2);
        fatherContentList.add(fatherContent1);
        fatherContentList.add(fatherContent3);

        myAdapter = new MyAdapter(context, fatherContentList);
        mRecyclerView.setAdapter(myAdapter);


    }


    //注意 一定要加这个  不然选项的答案就会叠加
    @Override
    protected void onResume() {
        super.onResume();
        grouplist.clear();
        listAfterOpitionOld.clear();
    }


    //点击提交跳转到B页面 把所选的选项的值也传过去
    public void submit(View view) {
        listAfterOpitionOld.addAll(myAdapter.getFatherContentList());
        for (FatherContent fatherContent :
                listAfterOpitionOld) {
            grouplist.add(fatherContent.getSelectIndex() + 1);
        }

        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        intent.putIntegerArrayListExtra("grouplist",grouplist);
        startActivity(intent);
    }
}
