package com.lzh.storm.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.lzh.storm.R;
import com.lzh.storm.action.ActionController;
import com.lzh.storm.action.bean.ActionConfig;
import com.lzh.storm.action.bean.socket.reply.Heart;
import com.lzh.storm.action.bean.socket.reply.Login;
import com.lzh.storm.module.socket.Channel;
import com.lzh.storm.module.socket.SocketChannel;
import com.lzh.storm.module.socket.TcpConnect;
import com.lzh.storm.utils.characterset.MD5;
import com.lzh.storm.utils.json.JsonCoder;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends ControllerActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }



    @Override
    public void skip(Class<? extends Activity> activity) {
        startActivity(new Intent(this,activity));
    }
}
