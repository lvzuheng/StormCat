package com.lzh.storm.module.http;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lzh on 2017/11/28.
 */

public class OkhttpApply {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient mOkHttpClient;
    private Callback mCallback;

    public OkhttpApply(long timetout) {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(timetout,TimeUnit.MILLISECONDS);
    }
    public OkhttpApply() {
        mOkHttpClient = new OkHttpClient();
    }


    public OkhttpApply setCallBack(Callback callback) {
        this.mCallback = callback;
        return this;
    }

    public void doPost(String url, MediaType type, String msg) {
        RequestBody requestBody = RequestBody.create(type, msg);
        doPost(url, requestBody);
    }

    public void doPost(String url, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Call call = mOkHttpClient.newCall(request);
        if (mCallback != null) {
            call.enqueue(mCallback);
        }
    }

    public String post(String url, MediaType type, String msg) {

        RequestBody body = RequestBody.create(type, msg);
        return post(url, body);
    }

    public String post(String url, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void doGet(String url) {
        Log.e("lzh","开始下载：啦啦啦啦");
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        if (mCallback != null) {
            call.enqueue(mCallback);
            Log.e("lzh","下载启动");
        }
    }


    public String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
