package com.lzh.storm.action.code.socket;

import android.util.Log;

import com.lzh.storm.action.CodeAnnotation;
import com.lzh.storm.action.bean.socket.recieve.LoginReply;
import com.lzh.storm.action.code.Protocol;
import com.lzh.storm.module.socket.Channel;
import com.lzh.storm.utils.json.JsonCoder;
import com.lzh.storm.view.activity.ControllerActivity;
import com.lzh.storm.view.activity.LoginActivity;
import com.lzh.storm.view.activity.MainActivity;

import io.reactivex.annotations.NonNull;

/**
 * Created by lzh on 2017/12/7.
 */

@CodeAnnotation("7001")
public class LoginAction implements Protocol{
    @Override
    public void excute(@NonNull String msg, @NonNull ControllerActivity activity, @NonNull Channel channel) {
        LoginReply loginReply = JsonCoder.parseJson(msg,LoginReply.class);
        Log.e("lzh","协议：");
        if(loginReply.isStatus()){
            Log.e("lzh","协议："+loginReply.isStatus());
            activity.skip(MainActivity.class);
        }
    }
}
