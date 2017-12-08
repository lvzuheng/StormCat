package com.lzh.storm.utils.system;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

/**
 * Created by lzh on 2017/12/8.
 */

public class ClassUtils {

    /**
     * @param packageName 包名
     * @param c           判断的类
     * @return List<Class>    包下指定的类
     * @Description: 根据包名获得该包以及子包下的所有类不查找jar包中
     */
    public static <T> List<Class> getSpecifyClass(Context context, String packageName, Class<T> c) {
        List<Class> classList = new ArrayList<Class>();
        Log.e("lzh", "packageName:" + packageName);
        try {
            DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = enumeration.nextElement();
                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    Class aClass = Class.forName(className);
                    Log.e("lzh", "aClass：" + aClass.getName());
                    if (c.isAssignableFrom(aClass)) {
                        if (!aClass.isInterface()) {
                            Log.e("lzh", "forName：" + aClass.getName());
                            classList.add(aClass);
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }

    /**
     * @param packageName 包名
     * @return List<Class>    包下所有类
     * @Description: 根据包名获得该包以及子包下的所有类不查找jar包中
     */
    public static List<Class> getAllClass(Context context, String packageName) {
        List<Class> classList = new ArrayList<>();
        Log.e("lzh", "packageName:" + packageName);
        try {
            DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = enumeration.nextElement();
                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    Class aClass = Class.forName(className);
                    classList.add(aClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }
}
