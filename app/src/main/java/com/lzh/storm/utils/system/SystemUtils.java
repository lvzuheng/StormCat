package com.lzh.storm.utils.system;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by lzh on 2017/11/30.
 */

public class SystemUtils {


    public static PackageInfo getAppInfo(Context context) {

        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packageInfo;
        } catch (Exception e) {
            Log.e("PackageInfo", "Exception", e);
        }
        return null;
    }


}
