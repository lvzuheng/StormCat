package com.lzh.storm.utils.testutils;

import android.content.Context;
import android.util.Log;

import com.lzh.storm.action.code.Protocol;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

/**
 * Created by lzh on 2017/12/7.
 */

public class ClassController {

    public static void getClass(Context context, String packageName) {
        List<String> classNameList = new ArrayList<String>();
        Log.e("lzh", "packageName:" + packageName);
        List<Object> classes = new ArrayList<>();
        try {

            DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = (String) enumeration.nextElement();
                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    Class aClass = Class.forName(className);
                    Log.e("lzh", "aClass：" + aClass.getName());
                    if (Protocol.class.isAssignableFrom(aClass)) {
                        if (!aClass.isInterface()) {
                            Protocol protocol = (Protocol) aClass.newInstance();
                            Log.e("lzh", "forName：" + protocol.getClass().getName());
                        }
                    }

                    classNameList.add(className);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
