package com.lzh.storm.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.lzh.storm.R;
import com.lzh.storm.module.socket.SocketChannel;
import com.lzh.storm.module.socket.TcpConnect;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lzh on 2017/12/7.
 */

public class LoginActivity extends ControllerActivity {
    @BindView(R.id.main_tv)
    TextView mainTv;
    @BindView(R.id.main_btn)
    Button mainBtn;
    private TcpConnect tcpConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.main_btn)
    public void onViewClicked() {
        connect();
    }

    private void connect() {
        tcpConnect = new TcpConnect("10.20.175.146", 6666, new SocketChannel());
        tcpConnect.connect();
    }


    @Override
    public void skip(Class<? extends Activity> activity) {
        startActivity(new Intent(this,activity));
    }
}
