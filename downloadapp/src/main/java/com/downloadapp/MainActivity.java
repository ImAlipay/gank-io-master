package com.downloadapp;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

/**
 * MainActivity:下载demo
 *
 * @author zpl
 * @date 2018/6/25
 */
public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (DownloadService.DownloadBind) service;
            mBinder.startDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private DownloadService.DownloadBind mBinder;
    private Receiver mReceiver;
    private ProgressBar mProgressBar;
    private NumKeyBoardView mKeyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        IntentFilter intentFilter = new IntentFilter();
        //为BroadcastReceiver指定action，使之用于接收同action的广播
        intentFilter.addAction("action");
        mReceiver = new Receiver();
        registerReceiver(mReceiver, intentFilter);

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        return;
                    }
                }

                Intent bindIntent = new Intent(MainActivity.this, DownloadService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final View view = View.inflate(MainActivity.this, R.layout.item_download, null);
                mProgressBar = view.findViewById(R.id.progressBar);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        mBinder.setShow(true);
                    }
                });
                alertDialog.show();

            }
        });
    }

    private void initView() {
        final EditText editText = (EditText) findViewById(R.id.edittext);
        final EditText editText1 = (EditText) findViewById(R.id.edittext1);
        final EditText editText2 = (EditText) findViewById(R.id.edittext2);
        mKeyView = (NumKeyBoardView) findViewById(R.id.kv_main);

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                editText.requestFocus();
                mKeyView.setStrReceiver(editText);
                mKeyView.setVisibility(View.VISIBLE);
                return true;
            }
        });

        editText1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                editText1.requestFocus();
                mKeyView.setStrReceiver(editText1);
                mKeyView.setVisibility(View.VISIBLE);
                return true;
            }
        });

        editText2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                editText2.requestFocus();
                mKeyView.setStrReceiver(editText2);
                mKeyView.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int progress = intent.getIntExtra("fraction", 0);
            Log.e("TAG", progress + "");
            mProgressBar.setProgress(progress);
        }
    }
}