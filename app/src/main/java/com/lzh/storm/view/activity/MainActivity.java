package com.lzh.storm.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.lzh.storm.R;
import com.lzh.storm.utils.system.Swapper;
import com.lzh.storm.view.SwapperAnannotation;

import butterknife.BindView;
import butterknife.ButterKnife;

@SwapperAnannotation(attr = Activity.class, value = "MainActivity")
public class MainActivity extends ControllerActivity {

    @BindView(R.id.main_fm)
    FrameLayout mainFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Swapper swapper = new Swapper(this, mainFm);
    }


    @Override
    public void skip(Class<? extends Activity> activity) {
    }
}
