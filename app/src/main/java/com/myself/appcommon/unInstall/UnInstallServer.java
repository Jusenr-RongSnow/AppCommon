package com.myself.appcommon.unInstall;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;

/**
 * Description: App卸载监听服务
 * Copyright  : Copyright (c) 2016
 * Email      : jusenr@163.com
 * Company    : 葡萄科技
 * Author     : Jusenr
 * Date       : 2017/3/16 10:19.
 */
public class UnInstallServer extends Service {

    SDCardListener[] listenners;

    @SuppressLint("SdCardPath")
    @Override
    public void onCreate() {
        super.onCreate();
        SDCardListener[] listenners = {
                new SDCardListener("/data/data/com.myself.appcommon", this),
                new SDCardListener(Environment.getExternalStorageDirectory() + File.separator + "test.txt", this)};
        this.listenners = listenners;

        for (SDCardListener listener : listenners) {
            listener.startWatching();
        }

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");
        Log.e("UnInstallServer", "onCreate: -####");
        if (file.exists())
            file.delete();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (SDCardListener listener : listenners) {
            listener.stopWatching();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
