package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ArrayList<Integer> grouplist;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        grouplist = new ArrayList<>();
        tv = findViewById(R.id.tv);
        grouplist=getIntent().getIntegerArrayListExtra("grouplist");

        tv.setText("你的选项是"+grouplist.toString());
    }
}
