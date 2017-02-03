package com.myself.appcommon.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * BitmapCache工具类
 *
 * @author widebluesky
 */
public class BitmapCacheUtil {

    private static final String TAG = "HP_BitmapCacheUtils";
    private static final int MB = 1024;
    private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 50;// MB

    /**
     * 保存Bitmap到SD卡
     *
     * @param bm
     * @param savePath
     * @param fileName
     */
    public void saveBitmapToSDCard(Bitmap bm, String savePath, String fileName) {
        if (bm == null) {
            Log.w(TAG, " trying to savenull bitmap");
            return;
        }

        if (!SDCardUtil.isSDCardExist()) {
            Log.w(TAG, " there is no sdcard");
            return;
        }
        // 判断sdcard上的空间
        if (FREE_SD_SPACE_NEEDED_TO_CACHE > SDCardUtil.getSDCardFreeSize()) {
            Log.w(TAG, "Low free space onsd, do not cache");
            return;
        }

        File path = Environment.getExternalStorageDirectory();

        File fileFolder = new File(path.getPath() + "/" + savePath);

        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }

        File saveFile = new File(fileFolder.getPath() + "/" + fileName);

        try {
            if (!saveFile.exists()) {
                saveFile.createNewFile();
                OutputStream outStream = new FileOutputStream(saveFile);
                bm.compress(Bitmap.CompressFormat.JPEG, 60, outStream);
                outStream.flush();
                outStream.close();
                Log.e("saveFile", saveFile.length() + "");
                Log.i(TAG, "Image saved tosd");
            }
        } catch (Exception e) {
            Log.w(TAG, e.getMessage());
        }

    }

    /**
     * 保存Bitmap到手机缓存
     *
     * @param bm
     * @param savePath
     * @param fileName
     * @param context
     */
    public void saveBitmapToMobileCache(Bitmap bm, String savePath,
                                        String fileName, Context context) {
        if (bm == null) {
            Log.w(TAG, " trying to savenull bitmap");
            return;
        }

        File path = context.getCacheDir();

        File fileFolder = new File(path.getPath() + "/" + savePath);

        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }

        File saveFile = new File(fileFolder.getPath() + "/" + fileName);

        try {
            if (!saveFile.exists()) {
                saveFile.createNewFile();
                OutputStream outStream = new FileOutputStream(saveFile);
                bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);

                outStream.flush();
                outStream.close();
                Log.e("saveFile", saveFile.length() + "");
                Log.i(TAG, "Image saved tosd");
            }
        } catch (Exception e) {
            Log.w(TAG, e.getMessage());
        }
    }
}
