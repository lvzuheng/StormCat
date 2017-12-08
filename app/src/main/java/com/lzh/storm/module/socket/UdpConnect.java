package com.lzh.storm.module.socket;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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

public class UdpConnect implements Connection {

    DatagramSocket socket;
    private String ip;
    private int port;
    private DatagramPacket datagramPacket;
    private InetAddress serverAddress;
    private Channel channel;


    public UdpConnect(@NonNull String ip, @NonNull int port, Channel callBack) {
        this.ip = ip;
        this.port = port;
    }


    @Override
    public synchronized DatagramSocket connect() {


        Observable.create(new ObservableOnSubscribe<DatagramPacket>() {
            @Override
            public void subscribe(ObservableEmitter<DatagramPacket> e) throws Exception {

                try {
                    socket = new DatagramSocket();
                    serverAddress = InetAddress.getByName(ip);
                    DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024, serverAddress, port);
                    e.onNext(datagramPacket);
                } catch (SocketException exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                } catch (UnknownHostException exception) {
                    exception.printStackTrace();
                    e.onError(exception);
                }

            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DatagramPacket>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DatagramPacket data) {
                        datagramPacket = data;
                        channel.success();
                        read();
                    }

                    @Override
                    public void onError(Throwable e) {
                        channel.error(e);
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
                while (socket != null && datagramPacket.getData() != null) {
                    socket.receive(datagramPacket);
                    byte[] bytes = new byte[datagramPacket.getLength() - datagramPacket.getOffset()];
                    Log.e("lzh", "接收到消息：" + new String(bytes).trim());
                    System.arraycopy(datagramPacket.getData(), datagramPacket.getOffset(), bytes, 0, bytes.length);
                    if (bytes.length > 0) {
                        e.onNext(bytes);
                        channel.callIdle(IdleEvent.Event.READ_IDLE);
                    }

                }
                e.onError(new Throwable("Error"));
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Observer<byte[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        channel.read(bytes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        channel.error(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return false;
    }

    @Override
    public boolean write(byte[] b) {
        Observable.just(b)
                .observeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .subscribe(new Observer<byte[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(byte[] bytes) {
                        if (ip != null && socket != null && bytes.length > 0) {
                            try {
                                socket.send(new DatagramPacket(bytes, bytes.length, serverAddress, port));
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

    public void disconnect() {
        if (socket != null)
            socket.close();
    }

    @Override
    public void release() {
        disconnect();
        socket = null;
        String ip = null;
        int port = -1;
        DatagramPacket datagramPacket = null;
        InetAddress serverAddress = null;
    }
}
