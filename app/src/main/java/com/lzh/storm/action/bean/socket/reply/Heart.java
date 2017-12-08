package com.lzh.storm.action.bean.socket.reply;

import com.lzh.storm.action.bean.CodeInfo;

/**
 * Created by lzh on 2017/12/6.
 */

public class Heart extends CodeInfo{
    public Heart(int pid,String uid){
        this.setPid(pid);
        this.setUsername(uid);
    }
}
