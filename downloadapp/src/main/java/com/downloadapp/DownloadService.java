package com.downloadapp;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;

public class DownloadService extends Service {

    public static final String URL_DOWNLOAD = "http://121.29.10.1/f5.market.mi-img.com/download/AppStore/0b8b552a1df0a8bc417a5afae3a26b2fb1342a909/com.qiyi.video.apk";

    private DownloadBind mDownloadBind = new DownloadBind();

    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mDownloadBind;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class DownloadBind extends Binder {

        private NotificationManager mNotifyManager;
        private NotificationCompat.Builder mBuilder;
        private boolean isShow;

        public void startDown() {
            download();
        }

        public void setShow(Boolean b) {
            isShow = b;
        }

        private void download() {
            mNotifyManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new NotificationCompat.Builder(DownloadService.this);
            mBuilder.setContentTitle("Picture Download")
                    .setContentText("Download in progress")
                    .setSmallIcon(R.mipmap.ic_launcher);

            OkGo.<File>get(URL_DOWNLOAD).tag(this).execute(new FileCallback() {

                @Override
                public void onStart(Request<File, ? extends Request> request) {
                    super.onStart(request);
                }

                @Override
                public void onSuccess(Response<File> response) {
                    if (isShow) {
                        mBuilder.setContentText("Download complete")
                                // Removes the progress bar
                                .setProgress(0, 0, false);
                        mNotifyManager.notify(0, mBuilder.build());
                    }
                    File file = response.body();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file),
                            "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                @Override
                public void downloadProgress(Progress progress) {
                    float fraction = progress.fraction;
                    Intent intent = new Intent("action");
                    sendBroadcast(intent.putExtra("fraction", (int) (fraction * 100)));
                    if (isShow) {
                        mBuilder.setProgress(100, (int) (fraction * 100), false);
                        mNotifyManager.notify(0, mBuilder.build());
                    }
                }
            });
        }

    }
}