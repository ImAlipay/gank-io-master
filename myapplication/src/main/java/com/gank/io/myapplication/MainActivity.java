package com.gank.io.myapplication;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OperaDatabase mOperaDatabase;
    private List<NotificationData> mQuery;
    private int start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOperaDatabase = new OperaDatabase(this);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationData data = new NotificationData();
                data.setDataType("1");
                data.setContent("hello");
                data.setTradeNo("djfdjfjkdjf");
                long l = SystemClock.currentThreadTimeMillis();
                data.setTimestamp(l + "");
                mOperaDatabase.insert(data);
            }
        });

        findViewById(R.id.bu2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationData data = new NotificationData();
                data.setDataType("2");
                data.setContent("oxxoxo");
                data.setTradeNo("dddddd");
                long l = SystemClock.currentThreadTimeMillis();
                data.setTimestamp(l + "");
                mOperaDatabase.insert(data);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<NotificationData> query = mOperaDatabase.query("1");
                System.out.println(query.toString());
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<NotificationData> query = mOperaDatabase.queryAll();
                System.out.println(query.size() + "");
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 10;
                if (mQuery != null && mQuery.size() > 0) {
                    NotificationData data = mQuery.get(mQuery.size() - 1);
                    start += mQuery.size();
                }
                mQuery = mOperaDatabase.queryLimit("1", page, start);
                System.out.println(mQuery.toString());
            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransactionMessageActivity.class);
                startActivity(intent);
            }
        });
    }

}