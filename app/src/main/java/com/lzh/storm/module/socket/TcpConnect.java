package com.lzh.storm.module.socket;

import android.os.SystemClock;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lzh on 2017/11/29.
 */

public class TcpConnect implements Connection {

    Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String ip;
    private int port;

    private Channel channel;

    private long readIdle = 0;
    private long writeIdle = 0;


    public TcpConnect(@NonNull String ip, @NonNull int port, Channel channel) {
        this.ip = ip;
        this.port = port;
        this.channel = channel;
        channel.setConnection(this);
    }

    @Override
    public synchronized Socket connect() {
        if (socket != null) {
            throw new IllegalStateException("socket is exist");
        }
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                long connectTime = System.currentTimeMillis();
                try {
                    socket = new Socket(ip, port);
                    socket.setKeepAlive(true);
                    while (socket != null && !socket.isConnected()) {
                        if (System.currentTimeMillis() - connectTime > 5 * 1000) {
                            e.onError(new Throwable("time out"));
                            return;
                        }
                        SystemClock.sleep(200);
                    }
                    inputStream = socket.getInputStream();
                    outputStream = socket.getOutputStream();
                    e.onNext(true);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        Log.e("lzh", "success：" + channel);
                        channel.channelActive();
                        read();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("lzh", "error：" + e.getMessage());
                        channel.error(e);
                        release();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return socket;
    }

    @Override
    public boolean read() {
        Observable.create(new ObservableOnSubscribe<byte[]>() {
            @Override
            public void subscribe(ObservableEmitter<byte[]> e) throws Exception {
                byte[] bytes = new byte[4 * 1024];
                int len = 0;
                while (socket != null && inputStream != null && channel != null) {
                    try {
                        while ((len = inputStream.read(bytes)) != -1) {
                            e.onNext(Arrays.copyOfRange(bytes, 0, len));
                            channel.callIdle(IdleEvent.Event.READ_IDLE);
                        }
                            socket.sendUrgentData(0xFF);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        e.onError(exception);
                        break;
                    }
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Observer<byte[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        Log.e("lzh", "onNext:" + socket.isConnected());
                        channel.read(bytes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {

                            Log.e("lzh", "onError:");
                            channel.error(e);
                            disconnect();

                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    }

                    @Override
                    public void onComplete() {
                        release();
                    }
                });
        return false;
    }


    @Override
    public synchronized boolean write(final byte[] b) {
        Log.e("lzh", "write：");
        Observable.just(b).subscribeOn(Schedulers.io()).subscribe(new Observer<byte[]>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(byte[] bytes) {
                Log.e("lzh", "写出：" + bytes.length);
                if (socket.isConnected() && bytes != null && bytes.length > 0) {
                    try {
                        outputStream.write(bytes);
                        outputStream.flush();
                        channel.callIdle(IdleEvent.Event.WRITE_IDLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return false;
    }

    public boolean isConnect() {
        return socket.isConnected();
    }

    public void disconnect() {
        try {
            if (inputStream != null)
                inputStream.close();
            if (outputStream != null)
                outputStream.close();
            if (socket != null)
                socket.close();
            if (channel != null)
                channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void release() {
        disconnect();
        inputStream = null;
        outputStream = null;
        socket = null;
        channel = null;
        String ip = null;
        int port = -1;
    }


}
