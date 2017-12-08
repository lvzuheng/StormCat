package com.lzh.storm.module.socket;

import android.util.Log;

import com.lzh.storm.action.ActionController;
import com.lzh.storm.action.bean.ActionConfig;
import com.lzh.storm.action.bean.socket.reply.Heart;
import com.lzh.storm.action.bean.socket.reply.Login;
import com.lzh.storm.utils.characterset.MD5;
import com.lzh.storm.utils.json.JsonCoder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by lzh on 2017/11/29.
 */

public class SocketChannel extends Channel {

    public SocketChannel(){
        Timeout(5000,5000,5000);
    }

    @Override
    public void success() {
        try {
            Login login = new Login();
            login.setUsername("zdm");
            login.setPassword(MD5.toMd5("123456"));
            login.setPid(ActionConfig.LOGIN_REQUEST);
            write(JsonCoder.formatToJson(login)+"\r\n");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void read(byte[] b) {
        Log.e("lzh","recieve:"+new String(b));
        ActionController.getInsatance().excute(new String(b),this);
    }

    @Override
    public void error(Throwable e) {

    }

    @Override
    protected void idle(IdleEvent.Event e) {
        super.idle(e);
        Log.e("lzh","idle:"+e.eventState);
        if(e.eventState.equals(IdleEvent.Event.WRITE_IDLE)){
            Heart heart = new Heart(ActionConfig.HEARTACTION,"zdm");
            String heartJson = JsonCoder.formatToJson(heart)+"\r\n";
            write(heartJson);
        }
    }
}
