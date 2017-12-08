package com.lzh.storm.action.code;

import com.lzh.storm.action.ActionController;
import com.lzh.storm.module.socket.Channel;
import com.lzh.storm.view.activity.ControllerActivity;

/**
 * Created by lzh on 2017/12/7.
 */

public interface Protocol {

    void excute(String msg, ControllerActivity activity, Channel channel);
}
