package com.lzh.storm.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.lzh.storm.action.ActionController;

/**
 * Created by lzh on 2017/12/7.
 */

public abstract class ControllerActivity extends Activity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionController.getInsatance().setControllerActivity(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ActionController.getInsatance().setControllerActivity(this);
    }

    public abstract void skip(Class<? extends Activity> activity) ;

    public String getClassName(){
        return this.getClass().getName();
    }

}
