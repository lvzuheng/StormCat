package com.lzh.storm.module.socket;

import java.io.Closeable;

/**
 * Created by lzh on 2017/11/29.
 */

public interface Connection {

    Closeable connect();
    boolean read();
    boolean write(byte[] b);
    void release();
}
