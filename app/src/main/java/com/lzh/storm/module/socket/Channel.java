package com.lzh.storm.module.socket;

import android.util.Log;

import io.reactivex.annotations.NonNull;

/**
 * Created by lzh on 2017/11/29.
 */

public abstract class Channel {
    public abstract void success();
    public abstract void read(byte[] b);
    public abstract void error(Throwable e);

    private Connection connection;

    public Channel(){

    }

    public void write(byte[] bytes){
        Log.e("lzh","写出的消息："+new String(bytes));
        connection.write(bytes);
    }
    public void write(String msg){
        connection.write(msg.getBytes());
    }

    protected void channelActive(){
        success();
        if(idleEvent!=null){
            idleEvent.start();
        }
    }


    private IdleEvent idleEvent;
    protected void idle(IdleEvent.Event e) {
    }
    public Channel Timeout(int readIdle,int writeIdle,int allIdle){
        idleEvent = new IdleEvent() {
            @Override
            public void onIdle(Event e) {
                idle(e);
            }
        };
        idleEvent.setIdle(readIdle,writeIdle,allIdle);
        return  this;
    }

    protected void Idle(@NonNull String idle,@NonNull  long wait){
    }

    protected void callIdle(String idle){
        idleEvent.callIdle(idle);
    }




    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }



    protected void close(){
        if(idleEvent != null){
            idleEvent.release();
            idleEvent = null;
        }
    }
}
