package com.lzh.storm.action;

import android.content.Context;
import android.util.Log;

import com.lzh.storm.action.code.Protocol;
import com.lzh.storm.utils.system.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

import dalvik.system.DexFile;


public class GainCode {


    public static Object FromCode(Context context, String code) {
        //判断是不是注解
        String packageName = CodeAnnotation.class.getPackage().getName();    //获得当前包名
        Log.e("lzh", "packageName:" + packageName);
        try {
            List<Class> allClass = ClassUtils.getSpecifyClass(context,packageName,Protocol.class);//获得当前包以及子包下的所有协议类
            Log.e("lzh", "classname:" + allClass.size());
            for (int i = 0; i < allClass.size(); i++) {
                if (allClass.get(i).isAnnotationPresent(CodeAnnotation.class)) {
                    Log.e("lzh","classname:"+allClass.get(i).getName()+",code:" + allClass.get(i).getAnnotation(CodeAnnotation.class)+","+code);
                    if (code .equals(((CodeAnnotation)allClass.get(i).getAnnotation(CodeAnnotation.class)).value())) {
                        Log.e("lzh", "classname:" + allClass.get(i).isAnnotationPresent(CodeAnnotation.class));
                        return allClass.get(i).newInstance();
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }



    public static List<Class> getAllClassByAnnotation(Context context, Class annotationClass) {

        List returnClassList = new ArrayList<Class>();
        //判断是不是注解
        if (annotationClass.isAnnotation()) {
            String packageName = annotationClass.getPackage().getName();    //获得当前包名
            System.out.println(packageName);
            try {
                List<Class> allClass = ClassUtils.getAllClass(context, packageName);//获得当前包以及子包下的所有类

                for (int i = 0; i < allClass.size(); i++) {
                    if (allClass.get(i).isAnnotationPresent(annotationClass)) {
                        returnClassList.add(allClass.get(i));
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return returnClassList;
    }

    private static List<Class> findClass(File directory, String packageName)
            throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClass(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }


    /**
     *
     * @Description: 根据包名获得该包以及子包下的所有类不查找jar包中
     * @return List<Class>    包下所有类
     * 无法使用
    //	 */
//	private static List<Class> getClasses(String packageName) throws ClassNotFoundException,IOException{
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		String path = packageName.replace(".", "/");
//		Enumeration<URL> resources = classLoader.getResources(path);
//		List<File> dirs = new ArrayList<File>();
//		Log.e("lzh","path:"+path);
//		while(resources.hasMoreElements()){
//			URL resource = resources.nextElement();
//			String newPath = resource.getFile().replace("%20", " ");
//			Log.e("lzh","newPath:"+newPath);
//			dirs.add(new File(newPath));
//		}
//		ArrayList<Class> classes = new ArrayList<Class>();
//		for(File directory:dirs){
//			classes.addAll(findClass(directory, packageName));
//		}
//		return classes;
//	}
}
