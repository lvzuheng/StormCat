package com.lzh.storm.utils.system;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lzh.storm.custom.view.SwapperButton;
import com.lzh.storm.view.SwapperAnannotation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.annotations.NonNull;

/**
 * Created by lzh on 2017/12/11.
 */

public class Swapper {


    private static Fragment fragmentOn;
    private static Map<String, Object> swapperMap = new HashMap<>();
    private Context context;
    private FrameLayout frameLayout;


    public Swapper(Context context) {
        this.context = context;
        View view = ((Activity) context).getWindow().getDecorView();
        init();
        initView(view);
    }

    public Swapper(Context context, FrameLayout frameLayout) {
        this.context = context;
        this.frameLayout = frameLayout;
        View view = ((Activity) context).getWindow().getDecorView();
        init();
        initView(view);
    }

    public void transForm(@NonNull Class<Activity> activity) {

        context.startActivity(new Intent(context, activity));

    }

    public void transForm(String swapper) {
        Object o = swapperMap.get(swapper);
        if (o instanceof Fragment)
            transForm((Fragment) o);
    }

    public void tranForm(View view) {
        if (view instanceof SwapperButton) {
            SwapperButton swapperButton = (SwapperButton) view;
            if (swapperButton.getSwapper() != null) {
                Object o = swapperMap.get(swapperButton.getSwapper());
                if (o instanceof Fragment) {
                    transForm((Fragment) o);
                } else if (o instanceof Class<?>) {
                    transForm((Class<Activity>) o);
                }
            }
        }
    }

    public void transForm(@NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((Activity) context).getFragmentManager().beginTransaction();
        if (fragmentOn != null) {
            fragmentTransaction.hide(fragmentOn);
        }
        try {
            if (!fragment.isAdded()) {
                fragmentTransaction.add(frameLayout.getId(), fragment).show(fragment).commit();
            } else {
                fragmentTransaction.show(fragment).commit(); // 隐藏当前的fragment，显示下一个
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentOn = fragment;
    }

    private void init() {
        try {
            List<Class> cList = ClassUtils.getAllClass(this.context, SwapperAnannotation.class.getPackage().getName());
            for (int i = 0; i < cList.size(); i++) {
                if (cList.get(i).isAnnotationPresent(SwapperAnannotation.class)) {
                    SwapperAnannotation sw = ((SwapperAnannotation) cList.get(i).getAnnotation(SwapperAnannotation.class));
                    if ((sw.attr().equals(Activity.class) && sw.value() != null)) {
                        swapperMap.put(sw.value(), cList.get(i));
                    } else if (sw.attr().equals(Fragment.class) && sw.value() != null) {
                        swapperMap.put(sw.value(), cList.get(i).newInstance());
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    private void initView(View view) {
        if (view instanceof ViewGroup) {

            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                if (viewchild instanceof SwapperButton) {
                    SwapperButton valueButton = (SwapperButton) viewchild;
                    valueButton.setOnSwapperListener(new SwapperButton.OnSwapperListener() {
                        @Override
                        public void swapperListener(View v) {
                            Object o;
                            if ((o = swapperMap.get(((SwapperButton) v).getSwapper())) != null) {
                                if (o instanceof Fragment) {
                                    transForm((Fragment) o);
                                } else if (o instanceof Class<?>) {
                                    transForm((Class<Activity>) o);
                                }
                            }
                        }
                    });
                    if (valueButton.isFirstDisplay()) {
                        valueButton.callOnSwapper();
                    }
                } else if (viewchild instanceof ViewGroup) {
                    initView(viewchild);
                }
            }

        }

    }
}
